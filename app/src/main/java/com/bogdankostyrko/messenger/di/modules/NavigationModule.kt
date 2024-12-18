package com.bogdankostyrko.messenger.di.modules

import com.bogdankostyrko.messenger.di.scopes.ApplicationScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @[ApplicationScope Provides]
    fun provideRouter(): Router {
        return cicerone.router
    }

    @[ApplicationScope Provides]
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}