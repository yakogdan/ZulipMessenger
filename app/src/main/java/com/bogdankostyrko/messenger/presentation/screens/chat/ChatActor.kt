package com.bogdankostyrko.messenger.presentation.screens.chat

import android.util.Log
import com.bogdankostyrko.data.messenger.network.api.DEFAULT_EVENT_ID
import com.bogdankostyrko.data.messenger.network.api.EMPTY_STRING
import com.bogdankostyrko.data.messenger.network.api.EVENT_TYPE_MESSAGE
import com.bogdankostyrko.data.messenger.network.api.EVENT_TYPE_REACTION
import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventType
import com.bogdankostyrko.messenger.domain.usecases.chat.AddReactionToMessageUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.DeleteEventQueueUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.GetMessageEventsUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.GetMessagesUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.GetReactionEventsUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.RegisterEventQueueUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.RemoveReactionToMessageUseCase
import com.bogdankostyrko.messenger.domain.usecases.chat.SendMessageUseCase
import com.bogdankostyrko.messenger.domain.usecases.people.UpdateYourPresenceUseCase
import com.bogdankostyrko.messenger.presentation.adapters.chat.toMessagesWithDateSeparator
import com.bogdankostyrko.messenger.presentation.adapters.mappers.toDelegate
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChatActor @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageEventsUseCase: GetMessageEventsUseCase,
    private val getReactionEventsUseCase: GetReactionEventsUseCase,
    private val registerEventQueueUseCase: RegisterEventQueueUseCase,
    private val deleteEventQueueUseCase: DeleteEventQueueUseCase,
    private val addReactionToMessageUseCase: AddReactionToMessageUseCase,
    private val removeReactionToMessageUseCase: RemoveReactionToMessageUseCase,
    private val updateYourPresenceUseCase: UpdateYourPresenceUseCase,
) : Actor<ChatCommand, ChatEvent>() {

    override fun execute(command: ChatCommand): Flow<ChatEvent> {
        return when (command) {
            is ChatCommand.LoadMessages -> getMessages(
                streamName = command.streamName,
                topicName = command.topicName,
            )

            is ChatCommand.SendMessage -> sendMessage(
                messageText = command.messageText,
                streamName = command.streamName,
                topicName = command.topicName,
            )

            is ChatCommand.ProcessReactionClick -> processReactionClick(
                messageId = command.messageId,
                emojiName = command.emojiName,
                messages = command.messages,
            )

            is ChatCommand.SubscribeOnEvents -> merge(
                flow {
                    subscribeOnMessageEvents(
                        streamName = command.streamName,
                        topicName = command.topicName,
                    )
                },
                flow {
                    subscribeToReactionsUpdate(
                        streamName = command.streamName,
                        topicName = command.topicName,
                    )
                },
            )

            is ChatCommand.UpdateYourPresence -> updateYourPresence(status = command.status)
        }
    }

    private fun sendMessage(
        messageText: String,
        streamName: String,
        topicName: String,
    ) = flow {
        runCatching {
            sendMessageUseCase.invoke(
                messageText = messageText,
                streamName = streamName,
                topicName = topicName,
            )
        }.onFailure {
            emit(ChatEvent.Domain.Error(throwable = it))
        }
    }

    private fun getMessages(
        streamName: String,
        topicName: String? = null,
    ) = flow {
        runCatching {
            getMessagesUseCase.invoke(
                streamName = streamName,
                topicName = topicName,
            ).toMessagesWithDateSeparator()
        }.fold(
            onSuccess = { messages ->
                emit(ChatEvent.Domain.DataLoaded(messages = messages))
            },
            onFailure = {
                emit(ChatEvent.Domain.Error(throwable = it))
            }
        )
    }

    private suspend fun FlowCollector<ChatEvent>.subscribeOnMessageEvents(
        streamName: String,
        topicName: String?,
    ) {
        runCatching {
            registerEventQueueUseCase.invoke(
                eventType = EVENT_TYPE_MESSAGE,
                streamName = streamName,
                topicName = topicName,
            )
        }.fold(
            onSuccess = { eventQueue ->
                var messageLastEventId = DEFAULT_EVENT_ID
                while (true) {
                    try {
                        val events = getMessageEventsUseCase.invoke(
                            queueId = eventQueue.queueId,
                            lastEventId = messageLastEventId,
                        )

                        events.forEach { messageEvent ->
                            Log.d("myTag", "messageEvent - ${messageEvent.type}")
                            when (messageEvent.type) {

                                MessageEventType.Add -> {
                                    val message = messageEvent.message
                                    if (message != null) {
                                        emit(
                                            ChatEvent.Domain.AddMessage(
                                                message = message.toDelegate(),
                                            )
                                        )
                                    }
                                }

                                MessageEventType.Delete -> {
                                    val messageId = messageEvent.messageId
                                    if (messageId != null) {
                                        emit(ChatEvent.Domain.DeleteMessage(messageId = messageId))
                                    }
                                }

                                MessageEventType.Edit -> {
                                    val messageId = messageEvent.messageId
                                    val messageText = messageEvent.content
                                    if (messageId != null && messageText != null) {
                                        emit(
                                            ChatEvent.Domain.EditMessage(
                                                messageId = messageId,
                                                messageText = messageText,
                                            )
                                        )
                                    }
                                }

                                MessageEventType.Unused -> {}
                            }
                        }
                        messageLastEventId = events.last().id
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        subscribeOnMessageEvents(
                            streamName = streamName,
                            topicName = topicName,
                        )
                    }
                }
            },
            onFailure = {}
        )
    }

    private suspend fun FlowCollector<ChatEvent>.subscribeToReactionsUpdate(
        streamName: String,
        topicName: String?,
    ) {
        var reactionQueueId = EMPTY_STRING
        runCatching {
            registerEventQueueUseCase.invoke(
                eventType = EVENT_TYPE_REACTION,
                streamName = streamName,
                topicName = topicName,
            )
        }.fold(
            onSuccess = { registeredEvent ->
                reactionQueueId = registeredEvent.queueId
                var reactionLastEventId = DEFAULT_EVENT_ID
                while (true) {
                    try {
                        val events = getReactionEventsUseCase.invoke(
                            queueId = reactionQueueId,
                            lastEventId = reactionLastEventId,
                        )
                        emit(ChatEvent.Domain.UpdateReactions(reactionEvents = events))
                        reactionLastEventId = events.last().id
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        subscribeToReactionsUpdate(streamName = streamName, topicName = topicName)
                    }
                }
            },
            onFailure = {
                if (reactionQueueId.isNotEmpty()) {
                    deleteEventQueueUseCase.invoke(queueId = reactionQueueId)
                }
            }
        )
    }

    private fun processReactionClick(
        messageId: Int,
        emojiName: String,
        messages: List<MessageModel>,
    ): Flow<ChatEvent> {

        val message = messages.find { messageItem -> messageItem.id == messageId }

        message?.let { messageItem ->
            val messageReactions = messageItem.reactions
            val reaction =
                messageReactions.find { it.emojiName == emojiName && it.userId == MY_USER_ID }
            return flow {
                runCatching {
                    if (reaction == null) {
                        addReactionToMessageUseCase.invoke(
                            messageId = messageId,
                            emojiName = emojiName,
                        ).onFailure {
                            Log.e(
                                "myTag",
                                "addReactionToMessageUseCase onFailure: $it",
                            )
                        }
                    } else {
                        removeReactionToMessageUseCase.invoke(
                            messageId = messageId,
                            emojiName = reaction.emojiName,
                        ).onFailure {
                            Log.e(
                                "myTag",
                                "removeReactionToMessageUseCase onFailure: $it",
                            )
                        }
                    }
                }
            }
        }
        return emptyFlow()
    }

    private fun updateYourPresence(status: String) = flow {
        runCatching {
            updateYourPresenceUseCase.invoke(status = status)
        }.onFailure {
            emit(ChatEvent.Domain.Error(throwable = it))
        }
    }
}