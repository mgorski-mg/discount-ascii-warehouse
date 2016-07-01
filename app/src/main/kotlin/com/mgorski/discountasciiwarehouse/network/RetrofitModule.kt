package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.BuildConfig
import com.mgorski.discountasciiwarehouse.network.ndjson.NdJsonConverterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import timber.log.Timber

@Module
class RetrofitModule {

    @Provides
    fun providesAsciiItemsProvider(service: AsciiWarehouseService)
            = AsciiItemsProvider(service)

    @Provides
    fun providesAsciiWarehouseService(retrofit: Retrofit)
            = retrofit.create(AsciiWarehouseService::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi)
            = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.END_POINT_URL)
            .addConverterFactory(NdJsonConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    @Provides
    fun providesOkHttp(loggingInterceptor: HttpLoggingInterceptor, cache: Cache?, @ProvidesCacheInterceptor cacheInterceptor: Interceptor)
            = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .build()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInerceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) })
        loggingInerceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInerceptor
    }

    @Provides
    fun providesMoshi()
            = Moshi.Builder().build()
}