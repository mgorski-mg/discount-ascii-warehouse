package com.mgorski.discountasciiwarehouse.view.messages

import android.app.Activity
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

class SnackbarMessagesManager(private val activity: Activity) : MessagesManager {
    override fun showMessage(messageId: Int, @StringRes actionTextId: Int?, action: ((View) -> Unit)?) {
        val snackbar = Snackbar.make(activity.window.decorView, messageId, Snackbar.LENGTH_SHORT)
        if (actionTextId != null && action != null) {
            snackbar.setAction(actionTextId, action)
        }
        snackbar.show()
    }
}