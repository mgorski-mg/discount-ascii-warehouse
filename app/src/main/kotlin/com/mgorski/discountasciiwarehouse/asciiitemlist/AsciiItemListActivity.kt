package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.asciiitemlist.di.ItemListComponent
import com.mgorski.discountasciiwarehouse.asciiitemlist.suggestions.SuggestionsCursorAdapter
import com.mgorski.discountasciiwarehouse.databinding.ActivityAsciiItemListBinding
import javax.inject.Inject

class AsciiItemListActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: AsciiItemListViewModel

    private lateinit var searchView: SearchView

    init {
        ItemListComponent.init(this).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAsciiItemListBinding>(this, R.layout.activity_ascii_item_list)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchView.setQuery(query, false)
            viewModel.onQueryChangedCommand(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_ascii_item_list, menu)
        setupSearchView(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_only_in_stock) {
            item.isChecked = !item.isChecked
            viewModel.onFilterChangedCommand(item.isChecked)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        ItemListComponent.destroy()
        super.onDestroy()
    }

    private fun setupSearchView(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true

        addResetQuery(searchItem)
        addSuggestions(searchView)
    }

    private fun addResetQuery(searchItem: MenuItem) {
        MenuItemCompat.setOnActionExpandListener(searchItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem) = true

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.onQueryChangedCommand("")
                return true
            }
        })
    }

    private fun addSuggestions(searchView: SearchView) {
        searchView.suggestionsAdapter = SuggestionsCursorAdapter(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String) = false

            override fun onQueryTextChange(newText: String): Boolean {
                val suggestions = viewModel.getFilteredSuggestions(newText)
                (searchView.suggestionsAdapter as SuggestionsCursorAdapter).changeSuggestions(suggestions)
                return true
            }
        })

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int) = false

            override fun onSuggestionClick(position: Int): Boolean {
                val suggestion = (searchView.suggestionsAdapter as SuggestionsCursorAdapter).getSuggestion(position)
                searchView.setQuery(suggestion, true)
                return true
            }
        })
    }
}