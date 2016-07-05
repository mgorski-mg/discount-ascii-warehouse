package com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions

import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.provider.BaseColumns
import android.support.v4.widget.CursorAdapter
import android.support.v4.widget.SimpleCursorAdapter
import com.mgorski.discountasciiwarehouse.R

class SuggestionsCursorAdapter(context: Context) : SimpleCursorAdapter(context, R.layout.item_suggestion, null, arrayOf(NAME), intArrayOf(R.id.text), CursorAdapter.NO_SELECTION) {

    companion object {
        private val NAME = "name"
    }

    fun getSuggestion(position: Int): String {
        val cursor = getItem(position) as Cursor
        return cursor.getString(cursor.getColumnIndex(NAME))
    }

    fun changeSuggestions(suggestions: List<String>) {
        val cursor = MatrixCursor(arrayOf(BaseColumns._ID, NAME))
        suggestions.forEachIndexed { i, suggestion -> cursor.addRow(arrayOf(i, suggestion)) }

        changeCursor(cursor)
    }
}