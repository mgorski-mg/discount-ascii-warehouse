package com.mgorski.discountasciiwarehouse.view.recyclerview.pagination

import android.support.v7.widget.RecyclerView

class PaginationRecyclerViewOnScrollListener(private val adapter: PaginationRecyclerViewAdapterDecoration, private val onLoadMore: (listener: PaginationRecyclerViewOnScrollListener) -> Unit) : RecyclerView.OnScrollListener() {

    private var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!isLoading && isLastItemDisplaying(recyclerView) && recyclerView.adapter == adapter) {
            loadingStarted()
            onLoadMore(this)
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }
    }

    fun loadingStarted() {
        adapter.addLoadingItem()
        isLoading = true
    }

    fun loadingFinished() {
        adapter.removeLoadingItem()
        isLoading = false
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        val childCount = recyclerView.layoutManager.childCount
        val child = recyclerView.getChildAt(childCount - 1)
        if (child != null) {
            val position = recyclerView.getChildAdapterPosition(child)
            return position == recyclerView.adapter.itemCount - 1 && child.bottom <= recyclerView.height
        }
        return false
    }
}