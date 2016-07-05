package com.mgorski.discountasciiwarehouse.asciiitemlist.di

import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemListViewModel
import com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions.SuggestionsStorage
import com.mgorski.discountasciiwarehouse.network.AsciiItemsProvider
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import dagger.Module
import dagger.Provides

@Module
class ItemListModule {

    @Provides
    fun providesAsciiItemListViewModel(asciiItemsProvider: AsciiItemsProvider, messagesManager: MessagesManager, suggestionsStorage: SuggestionsStorage)
            = AsciiItemListViewModel(asciiItemsProvider, messagesManager, suggestionsStorage)
}