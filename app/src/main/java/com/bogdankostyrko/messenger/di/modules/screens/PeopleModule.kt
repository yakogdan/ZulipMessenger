package com.bogdankostyrko.messenger.di.modules.screens

import com.bogdankostyrko.data.messenger.repositories.PeopleRepositoryImpl
import com.bogdankostyrko.data.messenger.repositories.PresenceRepositoryImpl
import com.bogdankostyrko.messenger.di.scopes.PeopleScope
import com.bogdankostyrko.messenger.domain.repositories.PeopleRepository
import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import dagger.Binds
import dagger.Module

@Module
interface PeopleModule {

    @[PeopleScope Binds]
    fun bindPeopleRepository(impl: PeopleRepositoryImpl): PeopleRepository

    @[PeopleScope Binds]
    fun bindPresenceRepository(impl: PresenceRepositoryImpl): PresenceRepository
}