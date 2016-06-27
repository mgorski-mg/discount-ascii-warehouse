package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableField
import com.mgorski.discountasciiwarehouse.model.AsciiItem

class AsciiItemViewModel(item: AsciiItem) {

    val item = ObservableField(item)
}