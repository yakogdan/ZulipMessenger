package com.bogdankostyrko.messenger.di.modules

import android.app.Application
import com.bogdankostyrko.data.messenger.database.AppDatabase
import com.bogdankostyrko.messenger.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @[ApplicationScope Provides]
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }
}