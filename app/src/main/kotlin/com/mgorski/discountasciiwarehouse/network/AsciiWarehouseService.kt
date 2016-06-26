package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.network.model.WebAsciiItem
import com.mgorski.discountasciiwarehouse.network.ndjson.NdJson
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface AsciiWarehouseService {

    @NdJson
    @GET("api/search")
    fun getAsciiItems(): Observable<List<WebAsciiItem>>

    @NdJson
    @GET("api/search")
    fun getAsciiItems(@Query("limit") limit: Int): Observable<List<WebAsciiItem>>
}