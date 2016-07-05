package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.databinding.ItemAsciiItemListBinding
import com.mgorski.discountasciiwarehouse.model.AsciiItem

class AsciiItemRecyclerViewAdapter(private val list: List<AsciiItem>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AsciiItemViewHolder(layoutInflater.inflate(R.layout.item_ascii_item_list, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AsciiItemViewHolder) {
            holder.binder.viewModel = AsciiItemViewModel(list[position])
            holder.binder.executePendingBindings()
        }
    }

    override fun getItemCount() = list.count()

    class AsciiItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binder: ItemAsciiItemListBinding = DataBindingUtil.bind(view)
    }
}