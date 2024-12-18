package com.bogdankostyrko.messenger.domain.usecases.chat

import com.bogdankostyrko.messenger.domain.models.events.reaction.ReactionEventModel
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import javax.inject.Inject

class GetReactionEventsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun invoke(
        queueId: String,
        lastEventId: Int,
    ): List<ReactionEventModel> = repository.getReactionEvents(
        queueId = queueId,
        lastEventId = lastEventId,
    )
}