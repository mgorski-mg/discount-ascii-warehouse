package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.network.model.WebAsciiItem
import com.mgorski.discountasciiwarehouse.network.ndjson.NdJson
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface AsciiWarehouseService {

    @NdJson
    @GET("api/search")
    fun getAsciiItems(@Query("limit") limit: Int? = null, @Query("skip") skip: Int? = null, @Query("q") tagsQuery: String? = null, @Query("onlyInStock") onlyInStock: Int? = null): Single<List<WebAsciiItem>>
}