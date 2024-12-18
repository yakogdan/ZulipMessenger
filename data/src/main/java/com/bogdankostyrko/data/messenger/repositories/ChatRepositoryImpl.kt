package com.bogdankostyrko.data.messenger.repositories

import com.bogdankostyrko.data.messenger.network.api.ANCHOR_NEWEST
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.data.messenger.network.mappers.chat.toModels
import com.bogdankostyrko.data.messenger.network.mappers.event.toModel
import com.bogdankostyrko.data.messenger.network.mappers.event.toModels
import com.bogdankostyrko.data.messenger.network.mappers.toResult
import com.bogdankostyrko.data.messenger.network.tools.createNarrow
import com.bogdankostyrko.data.messenger.network.tools.getEventQueryMap
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.models.events.RegisteredEventQueueModel
import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventModel
import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val zulipApiService: ZulipApiService,
) : ChatRepository {

    override suspend fun getMessages(
        streamName: String,
        topicName: String?,
    ): List<MessageModel> {

        val narrow = if (topicName == null) {
            createNarrow(streamName)
        } else {
            createNarrow(streamName, topicName)
        }

        return zulipApiService.getMessages(
            numBefore = 2000,
            numAfter = 0,
            anchor = ANCHOR_NEWEST,
            narrow = narrow
        ).messages
            .toModels()
    }

    override suspend fun sendMessage(
        messageText: String,
        streamName: String,
        topicName: String,
    ) {
        zulipApiService.sendMessage(
            messageText = messageText,
            streamName = streamName,
            topicName = topicName
        )
    }

    override suspend fun addReactionToMessage(
        messageId: Int,
        emojiName: String,
    ): Result<String> {
        return zulipApiService.addReactionToMessage(
            messageId = messageId,
            emojiName = emojiName,
        ).toResult()
    }

    override suspend fun removeReactionToMessage(
        messageId: Int,
        emojiName: String,
    ): Result<String> =
        zulipApiService.removeReactionToMessage(
            messageId = messageId,
            emojiName = emojiName
        ).toResult()

    override suspend fun registerEventQueue(
        eventType: String,
        streamName: String,
        topicName: String?,
    ): RegisteredEventQueueModel {
        val queryMap = getEventQueryMap(
            eventType = eventType,
            streamName = streamName,
            topicName = topicName,
        )
        return zulipApiService.registerEventQueue(queryMap).toModel()
    }

    override suspend fun deleteEventQueue(queueId: String) {
        zulipApiService.deleteEventQueue(queueId)
    }

    override suspend fun getMessageEvents(
        queueId: String,
        lastEventId: Int,
    ): List<MessageEventModel> =
        zulipApiService.getMessageEvents(
            queueId = queueId,
            lastEventId = lastEventId,
        ).events.toModels()

    override suspend fun getReactionEvents(
        queueId: String,
        lastEventId: Int
    ): List<ReactionEventModel> =
        zulipApiService.getReactionEvents(
            queueId = queueId,
            lastEventId = lastEventId,
        ).events.toModels()
}