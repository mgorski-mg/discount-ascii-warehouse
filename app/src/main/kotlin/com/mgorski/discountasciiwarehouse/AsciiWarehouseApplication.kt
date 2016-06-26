package com.mgorski.discountasciiwarehouse

import android.app.Application
import com.mgorski.discountasciiwarehouse.di.AppComponent

class AsciiWarehouseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this)
    }
}