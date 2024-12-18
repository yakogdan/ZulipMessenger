package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.models.events.message.MessageEventModel
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class GetMessageEventsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        queueId: String,
        lastEventId: Int,
    ): List<MessageEventModel> = repository.getMessageEvents(
        queueId = queueId,
        lastEventId = lastEventId,
    )
}