package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableArrayList
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.model.toAsciiItem
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewOnScrollListener
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AsciiItemListViewModel(private val service: AsciiWarehouseService) {

    val items = ObservableArrayList<AsciiItem>()

    init {
        service.getAsciiItems()
                .subscribeOn(Schedulers.io())
                .map { it.map { it.toAsciiItem() } }
                .subscribe {
                    items.addAll(it)
                }
    }

    val onLoadMoreCommand = { listener: PaginationRecyclerViewOnScrollListener ->
        var subscription = service.getAsciiItems(skip = items.count())
                .subscribeOn(Schedulers.io())
                .map { it.map { it.toAsciiItem() } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items.addAll(it); listener.loadingFinished() }
    }
}