package com.mgorski.discountasciiwarehouse.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemRecyclerViewAdapter
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.view.recyclerview.OffsetItemDecoration
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewAdapterDecoration
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewOnScrollListener

@BindingAdapter("items")
fun bindRecyclerViewItems(view: RecyclerView, list: List<AsciiItem>) {
    if (view.adapter == null) {
        view.layoutManager = GridLayoutManager(view.context, 2)
        view.adapter = PaginationRecyclerViewAdapterDecoration(AsciiItemRecyclerViewAdapter(list, view.context), view.context)
        view.addItemDecoration(OffsetItemDecoration(R.dimen.recycler_view_item_offset))
    } else {
        view.adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("onLoadMore")
fun bindPaginationRecyclerViewOnScrollListener(view: RecyclerView, onLoadMore: (PaginationRecyclerViewOnScrollListener) -> Unit) {
    if (view.adapter is PaginationRecyclerViewAdapterDecoration) {
        view.addOnScrollListener(PaginationRecyclerViewOnScrollListener(view.adapter as PaginationRecyclerViewAdapterDecoration, onLoadMore))
    } else {
        throw IllegalArgumentException("To use onLoadMore attr you must use PaginationRecyclerViewAdapterDecoration!")
    }
}