package com.bogdankostyrko.messenger.domain.usecases.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import javax.inject.Inject

class ReplaceSubStreamsInDBUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {
    suspend fun invoke(streams: List<StreamModel>) {
        repository.replaceSubscribedStreamsInDB(streams)
    }
}