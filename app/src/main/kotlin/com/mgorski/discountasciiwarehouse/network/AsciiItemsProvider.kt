package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.model.toAsciiItem
import rx.schedulers.Schedulers

class AsciiItemsProvider(private val service: AsciiWarehouseService) {
    fun getAsciiItems(limit: Int? = null, skip: Int? = null, tagsQuery: String? = null, onlyInStock: Int? = null) =
            service.getAsciiItems(limit, skip, tagsQuery, onlyInStock)
                    .subscribeOn(Schedulers.io())
                    .map { it.map { it.toAsciiItem() } }
}