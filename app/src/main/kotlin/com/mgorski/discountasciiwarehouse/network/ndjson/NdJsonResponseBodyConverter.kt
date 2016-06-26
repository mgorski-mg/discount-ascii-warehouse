package com.mgorski.discountasciiwarehouse.network.ndjson

import okhttp3.ResponseBody
import retrofit2.Converter

class NdJsonResponseBodyConverter<T>(private val moshiResponseBodyConverter: Converter<ResponseBody, T>) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody)
            = moshiResponseBodyConverter.convert(ResponseBody.create(value.contentType(), NdJsonUtil.convertToJsonArray(value.string())))
}