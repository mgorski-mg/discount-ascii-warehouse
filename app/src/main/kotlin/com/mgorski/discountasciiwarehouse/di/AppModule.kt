package com.mgorski.discountasciiwarehouse.di

import android.app.Application
import android.content.Context
import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemListViewModel
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun providesApplication(): Context = application

    @Provides
    fun providesAsciiItemListViewModel(service: AsciiWarehouseService) = AsciiItemListViewModel(service)
}