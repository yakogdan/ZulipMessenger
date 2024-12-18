package com.bogdankostyrko.messenger.domain.usecases.channels

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import javax.inject.Inject

class GetSubscribedStreamsDBUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {
    suspend fun invoke(): List<StreamModel> = repository.getSubscribedStreamsFromDB()
}