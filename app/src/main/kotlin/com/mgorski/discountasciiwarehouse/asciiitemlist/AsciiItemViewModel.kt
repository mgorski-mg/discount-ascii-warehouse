package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableField
import android.view.View
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.asciiitemlist.di.ItemListComponent
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import javax.inject.Inject

class AsciiItemViewModel(item: AsciiItem) {

    @Inject lateinit var messagesManager: MessagesManager

    val item = ObservableField(item)

    init {
        ItemListComponent.instance?.inject(this)
    }

    fun onBuyClickCommand(view: View) {
        messagesManager.showMessage(R.string.feature_not_implemented)
    }
}