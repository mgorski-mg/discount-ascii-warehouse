package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableArrayList
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.model.toAsciiItem
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewOnScrollListener
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AsciiItemListViewModel(private val service: AsciiWarehouseService) {

    private var query = ""

    val items = ObservableArrayList<AsciiItem>()

    init {
        loadItems()
    }

    val onLoadMoreCommand = { listener: PaginationRecyclerViewOnScrollListener -> loadItems(listener) }

    fun onQueryChangedCommand(query: String) {
        this.query = query
        items.clear()
        loadItems()
    }

    private fun loadItems(listener: PaginationRecyclerViewOnScrollListener? = null): Unit {
        service.getAsciiItems(skip = items.count(), tagsQuery = query)
                .subscribeOn(Schedulers.io())
                .map { it.map { it.toAsciiItem() } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    items.addAll(it)
                    listener?.loadingFinished()
                }
    }
}