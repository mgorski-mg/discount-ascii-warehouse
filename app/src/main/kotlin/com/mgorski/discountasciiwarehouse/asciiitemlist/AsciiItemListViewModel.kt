package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions.SuggestionsStorage
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.network.AsciiItemsProvider
import com.mgorski.discountasciiwarehouse.view.messages.MessagesManager
import com.mgorski.discountasciiwarehouse.view.recyclerview.pagination.PaginationRecyclerViewOnScrollListener
import rx.android.schedulers.AndroidSchedulers

class AsciiItemListViewModel(private val asciiItemsProvider: AsciiItemsProvider, private val messagesManager: MessagesManager, private val suggestionsStorage: SuggestionsStorage) {

    private var query = ""
    private var onlyInStock = false
    private val items: MutableList<AsciiItem> = arrayListOf()
    private val suggestions: MutableList<String>

    val filteredItems = ObservableArrayList<AsciiItem>()
    val isLoading = ObservableBoolean(false)

    init {
        suggestions = suggestionsStorage.loadSuggestions().toMutableList()
        loadItems()
    }

    val onLoadMoreCommand = { listener: PaginationRecyclerViewOnScrollListener -> loadItems(listener) }

    fun onQueryChangedCommand(query: String) {
        this.query = query
        items.clear()
        filteredItems.clear()
        loadItems()
    }

    fun onFilterChangedCommand(onlyInStock: Boolean) {
        this.onlyInStock = onlyInStock
        updateFilteredList()
    }

    fun getFilteredSuggestions(query: String) = suggestions.filter { it.contains(query, true) && !it.equals(query, true) }

    private fun loadItems(listener: PaginationRecyclerViewOnScrollListener? = null): Unit {
        isLoading.set(true)

        asciiItemsProvider.getAsciiItems(skip = items.count(), tagsQuery = query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newItems ->
                    if (newItems.size > 0) {
                        items.addAll(newItems)
                        updateFilteredList()
                        updateSuggestions(newItems)
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

    private fun updateFilteredList() {
        filteredItems.clear()
        filteredItems.addAll(items.filter { if (onlyInStock) it.stock > 0 else true })
    }

    private fun updateSuggestions(newItems: List<AsciiItem>) {
        val currentTags = newItems.map { it.tags }.flatMap { it }
        val newTags = currentTags.subtract(suggestions)
        if (newTags.any()) {
            suggestions.addAll(newTags)
            suggestions.sort()
            suggestionsStorage.saveSuggestions(suggestions)
        }
    }
}