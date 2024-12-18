package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class RemoveReactionToMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        messageId: Int,
        emojiName: String,
    ): Result<String> {
        return repository.removeReactionToMessage(
            messageId = messageId,
            emojiName = emojiName,
        )
    }
}