package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.network.AsciiItemsProvider
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewOnScrollListener
import rx.android.schedulers.AndroidSchedulers

class AsciiItemListViewModel(private val asciiItemsProvider: AsciiItemsProvider, private val messagesManager: MessagesManager) {

    private var query = ""

    val items = ObservableArrayList<AsciiItem>()
    var isLoading = ObservableBoolean(false)

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
        isLoading.set(true)

        asciiItemsProvider.getAsciiItems(skip = items.count(), tagsQuery = query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.size > 0) {
                        items.addAll(it)
                    } else {
                        messagesManager.showMessage(R.string.no_result, R.string.retry, { loadItems(listener) })
                    }
                    isLoading.set(false)
                    listener?.loadingFinished()
                }, {
                    messagesManager.showMessage(R.string.download_error, R.string.retry, { loadItems(listener) })
                    isLoading.set(false)
                    listener?.loadingFinished()
                })
    }
}