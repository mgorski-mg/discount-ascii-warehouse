package com.mgorski.discountasciiwarehouse.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.mgorski.discountasciiwarehouse.asciiitemlist.AsciiItemRecyclerViewAdapter
import com.mgorski.discountasciiwarehouse.model.AsciiItem

@BindingAdapter("items")
fun bindRecyclerViewItems(view: RecyclerView, list: List<AsciiItem>) {
    if (view.adapter == null) {
        view.layoutManager = GridLayoutManager(view.context, 2)
        view.adapter = AsciiItemRecyclerViewAdapter(list, view.context)
    } else {
        view.adapter.notifyDataSetChanged()
    }
}