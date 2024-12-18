package com.bogdankostyrko.messenger.domain.usecases.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import javax.inject.Inject

class GetStreamsByQueryUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {
    suspend fun invoke(
        query: String,
        isSubscribedStreams: Boolean,
    ): List<StreamModel> = repository.getStreamsByQuery(
        query = query,
        isSubscribedStreams = isSubscribedStreams,
    )
}