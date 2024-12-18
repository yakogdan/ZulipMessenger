package com.bogdankostyrko.messenger

import android.app.Application
import com.bogdankostyrko.messenger.di.components.DaggerApplicationComponent

class MessengerApp : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
    }
}