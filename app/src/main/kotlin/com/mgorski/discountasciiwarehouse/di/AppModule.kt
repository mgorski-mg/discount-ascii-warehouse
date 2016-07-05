package com.mgorski.discountasciiwarehouse.di

import android.app.Application
import android.content.Context
import com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions.SuggestionsStorage
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application

    @Provides
    fun providesSuggestionsStorage() = SuggestionsStorage(application)
}