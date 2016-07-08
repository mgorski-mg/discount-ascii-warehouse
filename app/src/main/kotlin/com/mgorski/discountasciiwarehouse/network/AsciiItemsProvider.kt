package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.model.toAsciiItem

class AsciiItemsProvider(private val service: AsciiWarehouseService) {
    fun getAsciiItems(limit: Int? = null, skip: Int? = null, tagsQuery: String? = null, onlyInStock: Int? = null)
            = service.getAsciiItems(limit, skip, tagsQuery, onlyInStock)
            .map { webItems -> webItems.map { webItem -> webItem.toAsciiItem() } }
}