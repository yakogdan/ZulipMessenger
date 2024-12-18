package com.bogdankostyrko.data.messenger.network.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class ZulipApiKeyInterceptor(
    username: String,
    apiKey: String,
) : Interceptor {

    private val headerValue = Credentials.basic(username, apiKey)

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", headerValue).build()
        )
    }
}