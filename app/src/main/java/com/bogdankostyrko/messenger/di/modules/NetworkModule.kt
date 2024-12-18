package com.bogdankostyrko.messenger.di.modules

import com.bogdankostyrko.data.messenger.network.api.API_KEY
import com.bogdankostyrko.data.messenger.network.api.BASE_URL
import com.bogdankostyrko.data.messenger.network.api.USERNAME
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.messenger.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @[ApplicationScope Provides]
    fun provideZulipApiService(): ZulipApiService = ZulipApiService(
        baseUrl = BASE_URL,
        username = USERNAME,
        apiKey = API_KEY,
    )
}