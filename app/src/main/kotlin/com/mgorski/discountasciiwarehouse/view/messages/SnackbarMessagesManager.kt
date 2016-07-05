package com.mgorski.discountasciiwarehouse.view.messages

import android.app.Activity
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import com.mgorski.discountasciiwarehouse.R

class SnackbarMessagesManager(private val activity: Activity) : MessagesManager {
    override fun showMessage(messageId: Int, @StringRes actionTextId: Int?, action: ((View) -> Unit)?) {
        val snackbar = Snackbar.make(activity.findViewById(R.id.toolbar) ?: activity.window.decorView, messageId, Snackbar.LENGTH_SHORT)
        if (actionTextId != null && action != null) {
            snackbar.setAction(actionTextId, action)
        }
        snackbar.show()
    }
}