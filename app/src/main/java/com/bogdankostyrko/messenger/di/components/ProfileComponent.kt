package com.bogdankostyrko.messenger.di.components

import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.di.modules.screens.ProfileModule
import com.bogdankostyrko.messenger.di.scopes.ProfileScope
import com.bogdankostyrko.messenger.presentation.screens.profile.ProfileFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

@ProfileScope
@Component(
    modules = [
        ProfileModule::class,
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ProfileComponent {

    fun inject(fragment: ProfileFragment)
    fun getRouter(): Router
    fun getZulipApiService(): ZulipApiService
}