package com.bogdankostyrko.messenger.domain.repositories

interface PresenceRepository {

    suspend fun updateYourPresence(status: String)
}