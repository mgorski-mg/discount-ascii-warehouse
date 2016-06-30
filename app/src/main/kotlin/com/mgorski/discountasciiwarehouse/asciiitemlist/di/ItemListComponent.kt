package com.mgorski.discountasciiwarehouse.asciiitemlist.di

import android.app.Activity
import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemListActivity
import com.mgorski.discountasciiwarehouse.di.ActivityModule
import com.mgorski.discountasciiwarehouse.di.AppComponent
import com.mgorski.discountasciiwarehouse.di.PerActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, ItemListModule::class))
interface ItemListComponent {

    companion object {
        fun build(activity: Activity): ItemListComponent {
            return DaggerItemListComponent.builder()
                    .appComponent(AppComponent.instance)
                    .activityModule(ActivityModule(activity))
                    .build()
        }
    }

    fun inject(asciiItemListActivity: AsciiItemListActivity)
}