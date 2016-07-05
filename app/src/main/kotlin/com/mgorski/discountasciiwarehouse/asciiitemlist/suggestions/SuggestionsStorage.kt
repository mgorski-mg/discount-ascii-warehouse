package com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions

import android.content.Context
import android.preference.PreferenceManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class SuggestionsStorage(context: Context) {

    companion object {
        private val SUGGESTIONS_KEY = "SUGGESTIONS_KEY"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val suggestionsJsonAdapter = Moshi.Builder().build().adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java))

    fun saveSuggestions(suggestions: List<String>) {
        val suggestionsJson = suggestionsJsonAdapter.toJson(suggestions)
        prefs.edit().putString(SUGGESTIONS_KEY, suggestionsJson).commit()
    }

    fun loadSuggestions(): List<String> {
        val suggestionsJson = prefs.getString(SUGGESTIONS_KEY, "[]")
        return suggestionsJsonAdapter.fromJson(suggestionsJson)
    }
}