package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.models.events.RegisteredEventQueueModel
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class RegisterEventQueueUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        eventType: String,
        streamName: String,
        topicName: String?,
    ): RegisteredEventQueueModel = repository.registerEventQueue(
        eventType = eventType,
        streamName = streamName,
        topicName = topicName,
    )
}