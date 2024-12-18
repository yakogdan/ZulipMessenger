package com.bogdankostyrko.messenger.domain.usecases.profile

import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend fun invoke(id: Int): UserModel = repository.getUserById(id)
}