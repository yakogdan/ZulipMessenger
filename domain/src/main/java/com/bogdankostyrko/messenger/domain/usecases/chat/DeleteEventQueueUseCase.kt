package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class DeleteEventQueueUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(queueId: String) {
        repository.deleteEventQueue(queueId)
    }
}