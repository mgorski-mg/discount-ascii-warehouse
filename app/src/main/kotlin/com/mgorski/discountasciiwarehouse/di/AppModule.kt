package com.mgorski.discountasciiwarehouse.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun providesApplication(): Context = application
}