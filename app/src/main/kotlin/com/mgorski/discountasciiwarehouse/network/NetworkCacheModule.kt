package com.mgorski.discountasciiwarehouse.network

import android.content.Context
import com.mgorski.discountasciiwarehouse.network.interceptor.CacheInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import java.io.File
import javax.inject.Qualifier

@Module
class NetworkCacheModule {

    companion object {
        private val CACHE_SIZE = 10 * 1024 * 1024.toLong() // 10 MB
        private val CACHE_NAME = "http-cache"
    }

    @Provides
    fun providesCache(context: Context): Cache? {
        try {
            return Cache(File(context.cacheDir, CACHE_NAME), CACHE_SIZE)
        } catch (ignore: Exception) {
            // TODO: add Timber
        }

        return null
    }

    @Provides
    @ProvidesCacheInterceptor
    fun providesOfflineCacheInterceptor()
            = CacheInterceptor()
}

@Qualifier
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProvidesCacheInterceptor