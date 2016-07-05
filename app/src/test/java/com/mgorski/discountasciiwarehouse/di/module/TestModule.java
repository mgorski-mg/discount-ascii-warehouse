package com.mgorski.discountasciiwarehouse.di.module;

import android.support.annotation.Nullable;

import com.mgorski.discountasciiwarehouse.network.ProvidesCacheInterceptor;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.Response;

@Module
public class TestModule {

    @Provides
    @Nullable
    public Cache providesCache() {
        return null;
    }

    @Provides
    @ProvidesCacheInterceptor
    public Interceptor providesOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
    }
}