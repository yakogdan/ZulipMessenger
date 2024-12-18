package com.bogdankostyrko.messenger.presentation.screens.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.elm.LceState

data class ChannelsState(
    val channels: LceState<List<DelegateItem>>,
    val isSubscribedStreams: Boolean,
)

sealed interface ChannelsEvent {

    sealed interface Ui : ChannelsEvent {

        data object LoadAllStreams : Ui
        data object LoadSubscribedStreams : Ui
        data class LoadStreamsByQuery(val query: String) : Ui

        data class OnStreamTopicsButtonClick(val stream: StreamModel) : Ui
        data class OnStreamItemClick(val streamName: String) : Ui
        data class OnTopicItemClick(val topic: TopicModel) : Ui
        data object OnSubscribedStreamsTabClick : Ui
        data object OnAllStreamsTabClick : Ui

        data class UpdateYourPresence(val status: String) : Ui
    }

    sealed interface Domain : ChannelsEvent {

        data class ChannelsLoaded(val channels: List<DelegateItem>) : Domain

        data class TopicsLoaded(
            val topics: List<TopicModel>,
            val streamId: Int,
        ) : Domain

        data class TopicsHidden(
            val channels: List<DelegateItem>,
            val streamId: Int,
        ) : Domain

        data class Error(val throwable: Throwable) : Domain
    }
}

sealed interface ChannelsCommand {

    data object LoadAllStreams : ChannelsCommand
    data object LoadSubscribedStreams : ChannelsCommand

    data class LoadStreamTopics(val stream: StreamModel) : ChannelsCommand

    data class HideStreamTopics(
        val stream: StreamModel,
        val channels: List<DelegateItem>,
    ) : ChannelsCommand

    data class ReplaceSubStreamsInDB(val streams: List<StreamModel>) : ChannelsCommand
    data class ReplaceAllStreamsInDB(val streams: List<StreamModel>) : ChannelsCommand

    data class UpdateYourPresence(val status: String) : ChannelsCommand
}

sealed interface ChannelsEffect {

    data object ShowError : ChannelsEffect
    data class NavigateToChat(
        val streamName: String,
        val topicName: String? = null,
    ) : ChannelsEffect
}