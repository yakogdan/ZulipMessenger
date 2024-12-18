package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class AddReactionToMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        messageId: Int,
        emojiName: String,
    ): Result<String> = repository.addReactionToMessage(
        messageId = messageId,
        emojiName = emojiName,
    )
}