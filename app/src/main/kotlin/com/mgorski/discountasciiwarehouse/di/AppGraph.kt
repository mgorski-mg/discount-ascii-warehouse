package com.mgorski.discountasciiwarehouse.di

import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemListActivity

interface AppGraph {
    fun inject(asciiItemListActivity: AsciiItemListActivity)
}