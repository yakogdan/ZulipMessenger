package com.bogdankostyrko.messenger.presentation.screens.channels

import android.util.Log
import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.presentation.adapters.channels.stream.StreamDelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.mappers.toDelegates
import com.bogdankostyrko.messenger.presentation.adapters.mappers.toModels
import com.bogdankostyrko.messenger.presentation.elm.LceState
import vivid.money.elmslie.core.store.dsl.ScreenDslReducer

class ChannelsReducer : ScreenDslReducer<
        ChannelsEvent,
        ChannelsEvent.Ui,
        ChannelsEvent.Domain,
        ChannelsState,
        ChannelsEffect,
        ChannelsCommand,
        >(
    ChannelsEvent.Ui::class,
    ChannelsEvent.Domain::class,
) {

    override fun Result.internal(event: ChannelsEvent.Domain) = when (event) {

        is ChannelsEvent.Domain.ChannelsLoaded -> {
            state { copy(channels = LceState.Content(event.channels)) }
            val data = state.channels
            if (data is LceState.Content) {
                @Suppress("UNCHECKED_CAST") val streams =
                    (data.content as List<StreamDelegateItem>).toModels()
                if (state.isSubscribedStreams) {
                    commands { +ChannelsCommand.ReplaceSubStreamsInDB(streams) }
                } else {
                    commands { +ChannelsCommand.ReplaceAllStreamsInDB(streams) }
                }
            } else {

            }
        }


        is ChannelsEvent.Domain.TopicsLoaded -> {
            when (val oldData = state.channels) {
                is LceState.Content -> {

                    val streams = oldData.content.toMutableList()
                    val currentStreamIndex = streams.indexOfFirst { it.id() == event.streamId }
                    val currentStream = streams[currentStreamIndex]

                    changeIsSelectedStatus(
                        currentStream = currentStream,
                        desiredValue = true,
                        streams = streams,
                        currentStreamIndex = currentStreamIndex,
                    )

                    streams.addAll(
                        index = currentStreamIndex + 1,
                        elements = event.topics.toDelegates(),
                    )

                    state { copy(channels = LceState.Content(streams)) }
                }

                else -> {}
            }
        }

        is ChannelsEvent.Domain.TopicsHidden -> {

            val streams = event.channels.toMutableList()
            val currentStreamIndex = streams.indexOfFirst { it.id() == event.streamId }
            val currentStream = streams[currentStreamIndex]

            changeIsSelectedStatus(
                currentStream = currentStream,
                desiredValue = false,
                streams = streams,
                currentStreamIndex = currentStreamIndex,
            )
            state { copy(channels = LceState.Content(streams)) }
        }

        is ChannelsEvent.Domain.Error -> {
            Log.e("myTAG", event.throwable.message.orEmpty())
            state { copy(channels = LceState.Error) }
            effects { +ChannelsEffect.ShowError }
        }
    }

    override fun Result.ui(event: ChannelsEvent.Ui) = when (event) {

        ChannelsEvent.Ui.LoadAllStreams -> {
            state { copy(channels = LceState.Loading) }
            commands { +ChannelsCommand.LoadAllStreams }
        }

        ChannelsEvent.Ui.LoadSubscribedStreams -> {
            state { copy(channels = LceState.Loading) }
            commands { +ChannelsCommand.LoadSubscribedStreams }
        }

        is ChannelsEvent.Ui.LoadStreamsByQuery -> {

        }

        is ChannelsEvent.Ui.OnStreamTopicsButtonClick -> {
            when (val oldData = state.channels) {

                is LceState.Content -> {
                    val streams = oldData.content
                    val currentStream = streams.find { it.id() == event.stream.id }
                    if (currentStream != null && (currentStream.content() as StreamModel).isSelected) {
                        commands {
                            +ChannelsCommand.HideStreamTopics(
                                stream = event.stream,
                                channels = streams,
                            )
                        }
                    } else {
                        commands {
                            +ChannelsCommand.LoadStreamTopics(
                                stream = event.stream
                            )
                        }
                    }
                }

                else -> {}
            }
        }

        is ChannelsEvent.Ui.OnStreamItemClick -> {
            effects {
                +ChannelsEffect.NavigateToChat(
                    streamName = event.streamName,
                )
            }
        }

        is ChannelsEvent.Ui.OnTopicItemClick -> {
            effects {
                +ChannelsEffect.NavigateToChat(
                    streamName = event.topic.parentStreamName,
                    topicName = event.topic.topicName,
                )
            }
        }

        ChannelsEvent.Ui.OnSubscribedStreamsTabClick -> {
            state { copy(isSubscribedStreams = true) }
            commands { +ChannelsCommand.LoadSubscribedStreams }
        }

        ChannelsEvent.Ui.OnAllStreamsTabClick -> {
            state { copy(isSubscribedStreams = false) }
            commands { +ChannelsCommand.LoadAllStreams }
        }

        is ChannelsEvent.Ui.UpdateYourPresence -> {
            commands { +ChannelsCommand.UpdateYourPresence(status = event.status) }
        }
    }
}