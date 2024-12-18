package com.bogdankostyrko.messenger.domain.usecases.people

import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.domain.repositories.PeopleRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: PeopleRepository
) {
    suspend fun invoke(): List<UserModel> = repository.getAllUsers()
}