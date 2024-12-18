package com.bogdankostyrko.messenger.di.components

import com.bogdankostyrko.data.messenger.database.AppDatabase
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.di.modules.screens.ChannelsModule
import com.bogdankostyrko.messenger.di.scopes.ChannelsScope
import com.bogdankostyrko.messenger.presentation.screens.channels.ChannelsFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

@ChannelsScope
@Component(
    modules = [
        ChannelsModule::class,
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ChannelsComponent {

    fun inject(fragment: ChannelsFragment)
    fun getRouter(): Router
    fun getZulipApiService(): ZulipApiService
    fun getAppDatabase(): AppDatabase
}