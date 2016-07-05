package com.mgorski.discountasciiwarehouse.network.ndjson

import com.squareup.moshi.Moshi
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type

/**
 * A [Converter.Factory][retrofit2.Converter.Factory] which creates NDJSON converts.
 *
 * [NdJson] annotation on the parameter is necessary to use request body converter and those on the method is necessary to use response body converter.
 *
 * @property moshi converter needed to parse and create JSON.
 */
class NdJsonConverterFactory private constructor(private val moshi: Moshi) : Converter.Factory() {

    companion object {
        /** Create an instance using a default [Moshi][com.squareup.moshi.Moshi] instance for conversion. */
        fun create() = create(Moshi.Builder().build())

        /** Create an instance using [Moshi][com.squareup.moshi.Moshi] for conversion.
         *
         * @param moshi converter needed to parse and create JSON
         */
        fun create(moshi: Moshi) = NdJsonConverterFactory(moshi)
    }

    private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        if (hasNdJsonAnnotations(annotations))
            return NdJsonResponseBodyConverter(moshiConverterFactory.responseBodyConverter(type, annotations, retrofit))
        else return null
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        if (hasNdJsonAnnotations(parameterAnnotations)) {
            return NdJsonRequestBodyConverter(moshi.adapter<Any>(type))
        } else return null
    }

    private fun hasNdJsonAnnotations(annotations: Array<Annotation>)
            = annotations.any() { it.annotationClass == NdJson::class }
}