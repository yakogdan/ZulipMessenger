package com.bogdankostyrko.messenger.di.modules.screens

import com.bogdankostyrko.data.messenger.database.AppDatabase
import com.bogdankostyrko.data.messenger.database.dao.StreamDao
import com.bogdankostyrko.data.messenger.repositories.ChannelsRepositoryImpl
import com.bogdankostyrko.data.messenger.repositories.PresenceRepositoryImpl
import com.bogdankostyrko.messenger.di.scopes.ChannelsScope
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ChannelsModule {

    @[ChannelsScope Binds]
    fun bindChannelsRepository(impl: ChannelsRepositoryImpl): ChannelsRepository

    @[ChannelsScope Binds]
    fun bindPresenceRepository(impl: PresenceRepositoryImpl): PresenceRepository

    companion object {

        @[ChannelsScope Provides]
        fun provideStreamDao(appDatabase: AppDatabase): StreamDao =
            appDatabase.streamDao()
    }
}