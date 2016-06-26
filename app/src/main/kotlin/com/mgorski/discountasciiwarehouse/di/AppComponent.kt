package com.mgorski.discountasciiwarehouse.di

import com.mgorski.discountasciiwarehouse.network.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RetrofitModule::class))
interface AppComponent : AppGraph {

    companion object {
        lateinit var instance: AppComponent

        fun init() {
            instance = DaggerAppComponent.builder()
                    .build()
        }
    }
}