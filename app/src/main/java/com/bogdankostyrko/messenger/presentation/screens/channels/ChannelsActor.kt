package com.bogdankostyrko.messenger.presentation.screens.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.domain.usecases.channels.GetAllStreamsApiUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.GetAllStreamsDBUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.GetSubscribedStreamsApiUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.GetSubscribedStreamsDBUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.GetTopicsInStreamUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.ReplaceAllStreamsInDBUseCase
import com.bogdankostyrko.messenger.domain.usecases.channels.ReplaceSubStreamsInDBUseCase
import com.bogdankostyrko.messenger.domain.usecases.people.UpdateYourPresenceUseCase
import com.bogdankostyrko.messenger.presentation.adapters.channels.topic.TopicDelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.adapters.mappers.toDelegates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class ChannelsActor @Inject constructor(
    private val getSubscribedStreamsApiUseCase: GetSubscribedStreamsApiUseCase,
    private val getSubscribedStreamsDBUseCase: GetSubscribedStreamsDBUseCase,
    private val getAllStreamsApiUseCase: GetAllStreamsApiUseCase,
    private val getAllStreamsDBUseCase: GetAllStreamsDBUseCase,
    private val replaceSubStreamsInDBUseCase: ReplaceSubStreamsInDBUseCase,
    private val replaceAllStreamsInDBUseCase: ReplaceAllStreamsInDBUseCase,
    private val getTopicsInStreamUseCase: GetTopicsInStreamUseCase,
    private val updateYourPresenceUseCase: UpdateYourPresenceUseCase,
) : Actor<ChannelsCommand, ChannelsEvent>() {

    override fun execute(command: ChannelsCommand): Flow<ChannelsEvent> {
        return when (command) {

            ChannelsCommand.LoadAllStreams -> getAllStreams()

            ChannelsCommand.LoadSubscribedStreams -> getSubscribedStreams()

            is ChannelsCommand.LoadStreamTopics -> getTopicsInStream(stream = command.stream)

            is ChannelsCommand.HideStreamTopics -> getChannelsWithoutStreamTopics(
                stream = command.stream,
                channels = command.channels,
            )

            is ChannelsCommand.ReplaceSubStreamsInDB -> replaceSubStreamsInDB(command.streams)

            is ChannelsCommand.ReplaceAllStreamsInDB -> replaceAllStreamsInDB(command.streams)

            is ChannelsCommand.UpdateYourPresence -> updateYourPresence(command.status)
        }
    }

    private fun getAllStreams() = flow {
        runCatching {
            val streamsFromDB = getAllStreamsDBUseCase.invoke()
            if (streamsFromDB.isNotEmpty()) {
                emit(
                    ChannelsEvent.Domain.ChannelsLoaded(
                        channels = streamsFromDB.toDelegates()

                    )
                )
            }
            getAllStreamsApiUseCase.invoke()
        }.fold(
            onSuccess = { streams ->
                emit(ChannelsEvent.Domain.ChannelsLoaded(channels = streams.toDelegates()))
            },
            onFailure = {
                emit(ChannelsEvent.Domain.Error(it))
            }
        )
    }

    private fun getSubscribedStreams() = flow {
        runCatching {
            val streamsFromDB = getSubscribedStreamsDBUseCase.invoke()
            if (streamsFromDB.isNotEmpty()) {
                emit(
                    ChannelsEvent.Domain.ChannelsLoaded(
                        channels = streamsFromDB.toDelegates()
                    )
                )
            }
            getSubscribedStreamsApiUseCase.invoke()
        }.fold(
            onSuccess = { streams ->
                emit(ChannelsEvent.Domain.ChannelsLoaded(channels = streams.toDelegates()))
            },
            onFailure = {
                emit(ChannelsEvent.Domain.Error(it))
            }
        )
    }

    private fun getTopicsInStream(stream: StreamModel) = flow {
        runCatching {
            getTopicsInStreamUseCase.invoke(
                streamId = stream.id,
                streamName = stream.streamName,
            )
        }.fold(
            onSuccess = { topics ->
                emit(
                    ChannelsEvent.Domain.TopicsLoaded(
                        topics = topics,
                        streamId = stream.id,
                    )
                )
            },
            onFailure = {
                emit(ChannelsEvent.Domain.Error(it))
            }
        )
    }

    private fun getChannelsWithoutStreamTopics(
        stream: StreamModel,
        channels: List<DelegateItem>,
    ): Flow<ChannelsEvent> = flow {
        val channelsWithoutTopics = channels.filter {
            if (it is TopicDelegateItem) {
                (it.content() as TopicModel).parentStreamName != stream.streamName
            } else {
                true
            }
        }
        emit(
            ChannelsEvent.Domain.TopicsHidden(
                channels = channelsWithoutTopics,
                streamId = stream.id,
            )
        )
    }

    private fun replaceSubStreamsInDB(streams: List<StreamModel>) = flow {
        runCatching {
            replaceSubStreamsInDBUseCase.invoke(streams)
        }.onFailure {
            emit(ChannelsEvent.Domain.Error(it))
        }
    }

    private fun replaceAllStreamsInDB(streams: List<StreamModel>) = flow {
        runCatching {
            replaceAllStreamsInDBUseCase.invoke(streams)
        }.onFailure {
            emit(ChannelsEvent.Domain.Error(it))
        }
    }

    private fun updateYourPresence(status: String) = flow {
        runCatching {
            updateYourPresenceUseCase.invoke(status = status)
        }.onFailure {
            emit(ChannelsEvent.Domain.Error(it))
        }
    }
}