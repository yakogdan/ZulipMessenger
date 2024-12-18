package com.bogdankostyrko.data.messenger.repositories

import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.data.messenger.network.mappers.user.toModel
import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val zulipApiService: ZulipApiService
) : ProfileRepository {

    override suspend fun getUserById(id: Int): UserModel {
        val userStatus = zulipApiService
            .getUserPresence(userIdOrEmail = id.toString()).presence.presenceInfo.status
        return zulipApiService.getUserById(id).user.toModel(userStatus)
    }
}