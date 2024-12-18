package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        messageText: String,
        streamName: String,
        topicName: String,
    ) {
        repository.sendMessage(
            messageText = messageText,
            streamName = streamName,
            topicName = topicName,
        )
    }
}