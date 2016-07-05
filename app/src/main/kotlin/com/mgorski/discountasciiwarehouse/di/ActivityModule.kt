package com.mgorski.discountasciiwarehouse.di

import android.app.Activity
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import com.mgorski.discountasciiwarehouse.view.messages.SnackbarMessagesManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun providesActivity() = activity

    @Provides
    @PerActivity
    fun providesMessagesManager(): MessagesManager = SnackbarMessagesManager(activity)
}