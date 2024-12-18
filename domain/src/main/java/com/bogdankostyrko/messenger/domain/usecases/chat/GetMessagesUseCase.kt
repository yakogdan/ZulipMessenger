package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        streamName: String,
        topicName: String? = null,
    ): List<MessageModel> = repository.getMessages(
        streamName = streamName,
        topicName = topicName,
    )
}