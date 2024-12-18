package com.bogdankostyrko.messenger.domain.usecases.people

import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import javax.inject.Inject

class UpdateYourPresenceUseCase @Inject constructor(
    private val repository: PresenceRepository
) {
    suspend fun invoke(status: String) {
        repository.updateYourPresence(status = status)
    }
}