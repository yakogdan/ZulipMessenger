package com.bogdankostyrko.data.messenger.repositories

import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import javax.inject.Inject

class PresenceRepositoryImpl @Inject constructor(
    private val zulipApiService: ZulipApiService,
) : PresenceRepository {

    override suspend fun updateYourPresence(status: String) {
        zulipApiService.updateYourPresence(status = status)
    }
}