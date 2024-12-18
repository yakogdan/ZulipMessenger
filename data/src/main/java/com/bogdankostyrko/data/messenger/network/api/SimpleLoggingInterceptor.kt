package com.bogdankostyrko.data.messenger.network.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class SimpleLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        Log.i("SimpleInterceptor", "--> ${request.method} ${request.url}")

        val response = chain.proceed(request)

        val responseBody = response.body
        val content = responseBody.string()

        Log.i("SimpleInterceptor", content)

        val newResponseBody = content.toResponseBody(responseBody.contentType())
        return response.newBuilder().body(newResponseBody).build()
    }
}