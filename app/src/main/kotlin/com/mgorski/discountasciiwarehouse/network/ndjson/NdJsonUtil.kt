package com.mgorski.discountasciiwarehouse.network.ndjson

/** Utility class used to transform Json and JsonArray to NdJson and NdJson to JsonArray.*/
class NdJsonUtil {
    companion object {
        /** Converts Json or JsonArray to NdJson.*/
        fun convertToNDJson(jsonString: String): String {
            var ndJsonString = jsonString.replace("},", "}\n").plus("\n")
            if (isJsonArray(jsonString)) {
                ndJsonString = ndJsonString.substring(1, jsonString.length - 1)
            }

            return ndJsonString
        }

        /** Converts NdJson to JsonArray.*/
        fun convertToJsonArray(ndJsonString: String): String {
            var jsonString = ndJsonString.split("\n").minus("").joinToString(",")
            jsonString = "[$jsonString]"

            return jsonString
        }

        private fun isJsonArray(jsonString: String) = jsonString.first() == '[' && jsonString.last() == ']'
    }
}