package com.bogdankostyrko.messenger.domain.repositories

import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.models.events.RegisteredEventQueueModel
import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel

interface ChatRepository {

    suspend fun getMessages(
        streamName: String,
        topicName: String?,
    ): List<MessageModel>

    suspend fun sendMessage(
        messageText: String,
        streamName: String,
        topicName: String,
    )

    suspend fun addReactionToMessage(
        messageId: Int,
        emojiName: String,
    ): Result<String>

    suspend fun removeReactionToMessage(
        messageId: Int,
        emojiName: String,
    ): Result<String>

    suspend fun registerEventQueue(
        eventType: String,
        streamName: String,
        topicName: String?,
    ): RegisteredEventQueueModel

    suspend fun deleteEventQueue(queueId: String)

    suspend fun getMessageEvents(
        queueId: String,
        lastEventId: Int,
    ): List<MessageEventModel>

    suspend fun getReactionEvents(
        queueId: String,
        lastEventId: Int,
    ): List<ReactionEventModel>
}