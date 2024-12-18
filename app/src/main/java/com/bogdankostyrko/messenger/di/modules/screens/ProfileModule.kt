package com.bogdankostyrko.messenger.di.modules.screens

import com.bogdankostyrko.data.messenger.repositories.PresenceRepositoryImpl
import com.bogdankostyrko.data.messenger.repositories.ProfileRepositoryImpl
import com.bogdankostyrko.messenger.di.scopes.ProfileScope
import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import com.bogdankostyrko.messenger.domain.repositories.ProfileRepository
import dagger.Binds
import dagger.Module

@Module
interface ProfileModule {

    @[ProfileScope Binds]
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @[ProfileScope Binds]
    fun bindPresenceRepository(impl: PresenceRepositoryImpl): PresenceRepository
}