package com.bogdankostyrko.messenger.di.components

import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.di.modules.screens.PeopleModule
import com.bogdankostyrko.messenger.di.scopes.PeopleScope
import com.bogdankostyrko.messenger.presentation.screens.people.PeopleFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

@PeopleScope
@Component(
    modules = [
        PeopleModule::class,
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface PeopleComponent {

    fun inject(fragment: PeopleFragment)
    fun getRouter(): Router
    fun getZulipApiService(): ZulipApiService
}