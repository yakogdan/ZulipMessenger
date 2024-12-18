package com.bogdankostyrko.messenger.di.modules.screens

import com.bogdankostyrko.data.messenger.repositories.ChatRepositoryImpl
import com.bogdankostyrko.data.messenger.repositories.PresenceRepositoryImpl
import com.bogdankostyrko.messenger.di.scopes.ChatScope
import com.bogdankostyrko.messenger.domain.repositories.ChatRepository
import com.bogdankostyrko.messenger.domain.repositories.PresenceRepository
import dagger.Binds
import dagger.Module

@Module
interface ChatModule {

    @[ChatScope Binds]
    fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository

    @[ChatScope Binds]
    fun bindPresenceRepository(impl: PresenceRepositoryImpl): PresenceRepository
}