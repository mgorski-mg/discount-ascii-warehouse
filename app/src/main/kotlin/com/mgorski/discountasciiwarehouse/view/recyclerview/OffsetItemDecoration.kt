package com.mgorski.discountasciiwarehouse.view.recyclerview

import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class OffsetItemDecoration(@DimenRes private val offsetId: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val dimensionPixelOffset = view.context.resources.getDimensionPixelOffset(offsetId)
        val position = parent.getChildAdapterPosition(view)
        val spanCount = getSpanCount(parent.layoutManager)

        if (position % spanCount == 0) {
            outRect.left = dimensionPixelOffset
        }

        if (position < spanCount) {
            outRect.top = dimensionPixelOffset
        }

        outRect.right = dimensionPixelOffset
        outRect.bottom = dimensionPixelOffset
    }

    private fun getSpanCount(layoutManager: RecyclerView.LayoutManager): Int {
        var spanCount = 0

        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
        } else if (layoutManager is LinearLayoutManager) {
            spanCount = 1
        }

        return spanCount
    }
}