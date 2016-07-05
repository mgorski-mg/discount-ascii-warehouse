package com.mgorski.discountasciiwarehouse.network.ndjson

/** Annotates function or function parameter, causing it to inform that [NdJsonConverterFactory] should be use to encode nad decode */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NdJson