package com.bogdankostyrko.data.messenger.repositories

import com.bogdankostyrko.data.messenger.network.api.ACTIVE_STATUS
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.data.messenger.network.mappers.user.toModels
import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.domain.repositories.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val zulipApiService: ZulipApiService,
) : PeopleRepository {

    override suspend fun getAllUsers(): List<UserModel> {
        val activeUsers = zulipApiService.getAllUsersPresence().presences
            .filter { it.value.presenceInfo.status == ACTIVE_STATUS }
            .map { it.key }
            .filter { email ->
                zulipApiService.getUserPresence(email).presence.presenceInfo.status == ACTIVE_STATUS
            }

        return zulipApiService.getAllUsers().members.toModels(activeUsers = activeUsers)
    }
}