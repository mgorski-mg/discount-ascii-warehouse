package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.databinding.ItemAsciiItemListBinding
import com.mgorski.discountasciiwarehouse.model.AsciiItem

class AsciiItemRecyclerViewAdapter(private val list: ObservableArrayList<AsciiItem>, context: Context) : RecyclerView.Adapter<AsciiItemRecyclerViewAdapter.BindingViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindingViewHolder(layoutInflater.inflate(R.layout.item_ascii_item_list, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binder.viewModel = AsciiItemViewModel(list[position])
        holder.binder.executePendingBindings()
    }

    override fun getItemCount() = list.count()

    class BindingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var binder: ItemAsciiItemListBinding = DataBindingUtil.bind(view)
    }
}