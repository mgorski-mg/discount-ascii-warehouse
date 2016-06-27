package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableArrayList
import com.mgorski.discountasciiwarehouse.model.AsciiItem

class AsciiItemListViewModel() {

    val items = ObservableArrayList<AsciiItem>()
}