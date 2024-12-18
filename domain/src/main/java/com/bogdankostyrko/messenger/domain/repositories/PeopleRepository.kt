package com.bogdankostyrko.messenger.domain.repositories

import com.bogdankostyrko.messenger.domain.models.UserModel

interface PeopleRepository {

    suspend fun getAllUsers(): List<UserModel>
}