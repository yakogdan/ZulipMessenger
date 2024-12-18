package com.bogdankostyrko.messenger.domain.repositories

import com.bogdankostyrko.messenger.domain.models.UserModel

interface ProfileRepository {

    suspend fun getUserById(id: Int): UserModel
}