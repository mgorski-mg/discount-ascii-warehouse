package com.mgorski.discountasciiwarehouse.view.recyclerview

import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class OffsetItemDecoration(@DimenRes private val offsetId: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val dimensionPixelOffset = view.context.resources.getDimensionPixelOffset(offsetId)

        outRect.left = dimensionPixelOffset
        outRect.right = dimensionPixelOffset
        outRect.bottom = dimensionPixelOffset
        outRect.top = dimensionPixelOffset
    }
}