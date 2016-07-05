package com.mgorski.discountasciiwarehouse.view.recyclerview.pagination

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgorski.discountasciiwarehouse.R

class PaginationRecyclerViewAdapterDecoration(private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val ITEM_LOADING = Int.MAX_VALUE
    }

    private val layoutInflater = LayoutInflater.from(context)
    private var isLoadingAdded = false

    override fun getItemViewType(position: Int) = if (isLoadingAdded && position == itemCount - 1) ITEM_LOADING else adapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_LOADING) {
            return LoadingViewHolder(layoutInflater.inflate(R.layout.item_loading, parent, false))
        }
        return adapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != ITEM_LOADING) {
            adapter.onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount() = if (isLoadingAdded) adapter.itemCount + 1 else adapter.itemCount

    fun addLoadingItem() {
        if (!isLoadingAdded) {
            isLoadingAdded = true
            notifyItemInserted(itemCount - 1)
        }
    }

    fun removeLoadingItem() {
        if (isLoadingAdded) {
            isLoadingAdded = false
            notifyItemRemoved(itemCount)
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}