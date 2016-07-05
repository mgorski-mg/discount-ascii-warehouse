package com.mgorski.discountasciiwarehouse.network

import com.mgorski.discountasciiwarehouse.network.ndjson.NdJsonUtil
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class NdJsonUtilTest {

    @Test
    fun shouldConvertJsonArrayToNdJsonString() {
        // Given
        val json = "[{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]},{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}]"
        val expected = "{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]}\n{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}\n"

        // When
        val result = NdJsonUtil.convertToNDJson(json);

        // Then
        MatcherAssert.assertThat(result,
                Matchers.`is`(expected))
    }

    @Test
    fun shouldConvertJsonToNdJsonString() {
        // Given
        val json = "{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]}"
        val expected = "{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]}\n"

        // When
        val result = NdJsonUtil.convertToNDJson(json);

        // Then
        MatcherAssert.assertThat(result,
                Matchers.`is`(expected))
    }

    @Test
    fun shouldConvertNdJsonToJsonArray() {
        // Given
        val ndjson = "{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]}\n{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}\n"
        val expected = "[{\"type\":\"face\",\"id\":\"0-4itmqrfkz6e9izfr\",\"size\":24,\"price\":379,\"face\":\"( .-. )\",\"stock\":7,\"tags\":[\"flat\",\"bored\"]},{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}]"

        // When
        val result = NdJsonUtil.convertToJsonArray(ndjson);

        // Then
        MatcherAssert.assertThat(result,
                Matchers.`is`(expected))
    }

    @Test
    fun shouldConvertNdJsonWithOneElementToJsonArray() {
        // Given
        val ndjson = "{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}\n"
        val expected = "[{\"type\":\"face\",\"id\":\"1-vw21ngfdtn50o1or\",\"size\":19,\"price\":487,\"face\":\"( .o.)\",\"stock\":0,\"tags\":[\"suprised\",\"consectetur\"]}]"

        // When
        val result = NdJsonUtil.convertToJsonArray(ndjson);

        // Then
        MatcherAssert.assertThat(result,
                Matchers.`is`(expected))
    }
}