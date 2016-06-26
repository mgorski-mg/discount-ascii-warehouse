package com.mgorski.discountasciiwarehouse.network.ndjson

import com.squareup.moshi.JsonAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter

class NdJsonRequestBodyConverter<T>(private val jsonAdapter: JsonAdapter<T>) : Converter<T, RequestBody> {

    companion object {
        private var MEDIA_TYPE = MediaType.parse("application/x-json-stream")
    }

    override fun convert(value: T): RequestBody {
        val jsonString = jsonAdapter.toJson(value)
        return RequestBody.create(MEDIA_TYPE, NdJsonUtil.convertToNDJson(jsonString))
    }
}