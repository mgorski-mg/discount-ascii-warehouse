package com.mgorski.discountasciiwarehouse

import android.app.Application
import com.mgorski.discountasciiwarehouse.di.AppComponent
import timber.log.Timber

class AsciiWarehouseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}