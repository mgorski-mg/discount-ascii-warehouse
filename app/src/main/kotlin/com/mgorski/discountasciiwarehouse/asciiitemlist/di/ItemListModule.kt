package com.mgorski.discountasciiwarehouse.asciiitemlist.di

import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemListViewModel
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import dagger.Module
import dagger.Provides

@Module
class ItemListModule {

    @Provides
    fun providesAsciiItemListViewModel(service: AsciiWarehouseService, messagesManager: MessagesManager) = AsciiItemListViewModel(service, messagesManager)
}