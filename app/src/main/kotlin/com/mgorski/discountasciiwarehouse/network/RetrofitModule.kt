package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.BuildConfig
import com.mgorski.discountasciiwarehouse.network.ndjson.NdJsonConverterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

@Module
class RetrofitModule {

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
    fun providesOkHttp(loggingInterceptor: HttpLoggingInterceptor)
            = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor { it.proceed(it.request())}
            .build()

    @Provides
    fun providesHttpLoggingInterceptor()
            = HttpLoggingInterceptor()

    @Provides
    fun providesMoshi()
            = Moshi.Builder().build()
}