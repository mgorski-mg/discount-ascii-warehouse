package com.mgorski.discountasciiwarehouse.view.messages

import android.support.annotation.StringRes
import android.view.View

interface MessagesManager {
    fun showMessage(@StringRes messageId: Int, @StringRes actionTextId: Int? = null, action: ((View) -> Unit)? = null)
}