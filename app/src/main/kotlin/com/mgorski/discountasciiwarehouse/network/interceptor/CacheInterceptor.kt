package com.mgorski.discountasciiwarehouse.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

open class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val cacheControl = CacheControl.Builder()
                .maxStale(1, TimeUnit.HOURS)
                .build()
        request = request.newBuilder()
                .cacheControl(cacheControl)
                .build()

        return chain.proceed(request)
    }
}