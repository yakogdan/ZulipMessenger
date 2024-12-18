package com.bogdankostyrko.messenger.presentation.screens.chat

import com.bogdankostyrko.data.messenger.network.api.MY_USER_ID
import com.bogdankostyrko.data.messenger.network.mappers.chat.getEmoji
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.models.ReactionModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventOperation.Add
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventOperation.Remove
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventOperation.Unused
import com.bogdankostyrko.messenger.presentation.adapters.chat.message.MessageDelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.chat.toMessagesWithDateSeparator
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ChatReducer : ScreenDslReducer<
        ChatEvent,
        ChatEvent.Ui,
        ChatEvent.Domain,
        ChatState,
        ChatEffect,
        ChatCommand,
        >(
    ChatEvent.Ui::class,
    ChatEvent.Domain::class,
) {

    override fun Result.internal(event: ChatEvent.Domain) = when (event) {

        is ChatEvent.Domain.DataLoaded -> {
            state { copy(messages = LceState.Content(event.messages)) }
        }

        is ChatEvent.Domain.Error -> {
            state { copy(messages = LceState.Error) }
            effects { +ChatEffect.ShowError }
        }

        is ChatEvent.Domain.AddMessage -> {
            val currentMessageList = (state.messages as LceState.Content).content

            state {
                copy(
                    messages = LceState.Content(
                        content = getUpdatedMessagesAfterAdding(
                            messages = currentMessageList,
                            newMessage = event.message.content() as MessageModel,
                        )
                    )
                )
            }
        }

        is ChatEvent.Domain.DeleteMessage -> {
            val currentMessageList = (state.messages as LceState.Content).content

            state {
                copy(
                    messages = LceState.Content(
                        getUpdatedMessagesAfterDeletion(
                            messages = currentMessageList,
                            messageId = event.messageId,
                        )
                    )
                )
            }
        }

        is ChatEvent.Domain.EditMessage -> {
            val currentMessageList = (state.messages as LceState.Content).content

            state {
                copy(
                    messages = LceState.Content(
                        getUpdatedMessagesAfterEditing(
                            messages = currentMessageList,
                            messageId = event.messageId,
                            messageText = event.messageText,
                        )
                    )
                )
            }
        }

        is ChatEvent.Domain.UpdateReactions -> {
            event.reactionEvents.forEach { reactionEvent ->
                val currentMessageList = (state.messages as LceState.Content).content

                state {
                    copy(
                        messages = LceState.Content(
                            getMessagesAfterUpdatingReactions(
                                messages = currentMessageList,
                                reactionEvent = reactionEvent,
                            )
                        )
                    )
                }
            }
        }
    }

    override fun Result.ui(event: ChatEvent.Ui) = when (event) {

        is ChatEvent.Ui.LoadMessages -> {
            state {
                copy(messages = LceState.Loading)
            }
            commands {
                +ChatCommand.LoadMessages(
                    streamName = event.streamName,
                    topicName = event.topicName,
                )
            }
        }

        ChatEvent.Ui.OnBackButtonClick -> {
            effects { +ChatEffect.NavigateToBack }
        }

        is ChatEvent.Ui.OnSendButtonClick -> {
            commands {
                +ChatCommand.SendMessage(
                    messageText = event.messageText,
                    streamName = event.streamName,
                    topicName = event.topicName,
                )
            }
        }

        is ChatEvent.Ui.OnReactionClick -> {

            val messages = (state.messages as LceState.Content).content
                .filterIsInstance<MessageDelegateItem>()
                .map { it.content() as MessageModel }

            commands {
                +ChatCommand.ProcessReactionClick(
                    messageId = event.messageId,
                    emojiName = event.emojiName,
                    messages = messages,
                )
            }
        }

        is ChatEvent.Ui.SubscriptionOnEvents -> {
            commands {
                +ChatCommand.SubscribeOnEvents(
                    streamName = event.streamName,
                    topicName = event.topicName,
                )
            }
        }

        is ChatEvent.Ui.UpdateYourPresence -> {
            commands { +ChatCommand.UpdateYourPresence(status = event.status) }
        }
    }

    private fun getUpdatedMessagesAfterAdding(
        messages: List<DelegateItem>,
        newMessage: MessageModel,
    ): List<DelegateItem> {
        val oldList = messages
            .filterIsInstance<MessageDelegateItem>()
            .map { it.content() as MessageModel }
            .toMutableList()
        oldList.add(element = newMessage)
        return oldList.toMessagesWithDateSeparator()
    }

    private fun getUpdatedMessagesAfterDeletion(
        messages: List<DelegateItem>,
        messageId: Int,
    ): List<DelegateItem> {
        val oldList = messages
            .filterIsInstance<MessageDelegateItem>()
            .map { it.content() as MessageModel }
            .toMutableList()
        oldList.removeIf { it.id == messageId }
        return oldList.toMessagesWithDateSeparator()
    }

    private fun getUpdatedMessagesAfterEditing(
        messages: List<DelegateItem>,
        messageId: Int,
        messageText: String,
    ): List<DelegateItem> {
        return messages
            .filterIsInstance<MessageDelegateItem>()
            .map { it.content() as MessageModel }
            .map { message ->
                if (message.id == messageId) {
                    message.copy(text = messageText)
                } else {
                    message
                }
            }
            .toMessagesWithDateSeparator()
    }

    private fun getMessagesAfterUpdatingReactions(
        messages: List<DelegateItem>,
        reactionEvent: ReactionEventModel,
    ): List<DelegateItem> {
        return messages
            .filterIsInstance<MessageDelegateItem>()
            .map { it.content() as MessageModel }
            .map { message ->
                if (message.id == reactionEvent.messageId) {
                    when (reactionEvent.operation) {

                        Add -> {
                            val reactions = message.reactions.toMutableList()

                            reactions.add(
                                ReactionModel(
                                    emojiCode = reactionEvent.emojiCode.getEmoji(),
                                    emojiName = reactionEvent.emojiName,
                                    userId = reactionEvent.userId,
                                    isMyReaction = reactionEvent.userId == MY_USER_ID,
                                )
                            )

                            message.copy(reactions = reactions)
                        }

                        Remove -> {
                            val reactions = message.reactions.toMutableList()

                            reactions.removeIf { reaction ->
                                reaction.userId == reactionEvent.userId &&
                                        reaction.emojiName == reactionEvent.emojiName
                            }

                            message.copy(reactions = reactions)
                        }

                        Unused -> {
                            message
                        }
                    }
                } else {
                    message
                }
            }
            .toMessagesWithDateSeparator()
    }
}