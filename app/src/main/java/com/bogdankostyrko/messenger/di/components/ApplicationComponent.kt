package com.bogdankostyrko.messenger.di.components

import android.app.Application
import com.bogdankostyrko.data.messenger.database.AppDatabase
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.MessengerApp
import com.bogdankostyrko.messenger.di.modules.DatabaseModule
import com.bogdankostyrko.messenger.di.modules.NavigationModule
import com.bogdankostyrko.messenger.di.modules.NetworkModule
import com.bogdankostyrko.messenger.di.scopes.ApplicationScope
import com.bogdankostyrko.messenger.presentation.activities.MainActivity
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(application: MessengerApp)
    fun getRouter(): Router
    fun getZulipApiService(): ZulipApiService
    fun getAppDatabase(): AppDatabase

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}