package com.bogdankostyrko.messenger.di.components

import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.di.modules.screens.ChatModule
import com.bogdankostyrko.messenger.di.scopes.ChatScope
import com.bogdankostyrko.messenger.presentation.screens.chat.ChatFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

@ChatScope
@Component(
    modules = [
        ChatModule::class,
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ChatComponent {

    fun inject(fragment: ChatFragment)
    fun getRouter(): Router
    fun getZulipApiService(): ZulipApiService
}