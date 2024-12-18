package com.bogdankostyrko.messenger.domain.usecases.channels

import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import javax.inject.Inject

class GetTopicsInStreamUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {
    suspend fun invoke(
        streamId: Int,
        streamName: String,
    ): List<TopicModel> = repository.getTopicsInStream(
        streamId = streamId,
        streamName = streamName,
    )
}