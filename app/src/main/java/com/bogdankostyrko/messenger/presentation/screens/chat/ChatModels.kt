package com.bogdankostyrko.messenger.presentation.screens.chat

import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel
import com.bogdankostyrko.messenger.presentation.adapters.delegate.DelegateItem
import com.bogdankostyrko.messenger.presentation.elm.LceState

data class ChatState(
    val messages: LceState<List<DelegateItem>>,
)

sealed interface ChatEvent {

    sealed interface Ui : ChatEvent {

        data class LoadMessages(
            val streamName: String,
            val topicName: String? = null,
        ) : Ui

        data object OnBackButtonClick : Ui

        data class OnSendButtonClick(
            val messageText: String,
            val streamName: String,
            val topicName: String,
        ) : Ui

        data class OnReactionClick(
            val messageId: Int,
            val emojiName: String,
        ) : Ui

        data class SubscriptionOnEvents(
            val streamName: String,
            val topicName: String?,
        ) : Ui

        data class UpdateYourPresence(val status: String) : Ui
    }

    sealed interface Domain : ChatEvent {

        data class DataLoaded(val messages: List<DelegateItem>) : Domain

        data class AddMessage(val message: DelegateItem) : Domain
        data class DeleteMessage(val messageId: Int) : Domain
        data class EditMessage(val messageId: Int, val messageText: String) : Domain

        data class UpdateReactions(val reactionEvents: List<ReactionEventModel>) : Domain

        data class Error(val throwable: Throwable) : Domain
    }
}

sealed interface ChatCommand {

    data class LoadMessages(
        val streamName: String,
        val topicName: String? = null,
    ) : ChatCommand

    data class SendMessage(
        val messageText: String,
        val streamName: String,
        val topicName: String,
    ) : ChatCommand

    data class ProcessReactionClick(
        val messageId: Int,
        val emojiName: String,
        val messages: List<MessageModel>,
    ) : ChatCommand

    data class SubscribeOnEvents(
        val streamName: String,
        val topicName: String?,
    ) : ChatCommand

    data class UpdateYourPresence(val status: String) : ChatCommand
}

sealed interface ChatEffect {

    data object ShowError : ChatEffect
    data object NavigateToBack : ChatEffect
}