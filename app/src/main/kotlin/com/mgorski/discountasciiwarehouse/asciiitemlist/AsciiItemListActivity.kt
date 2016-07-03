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

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true

        MenuItemCompat.setOnActionExpandListener(searchItem, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem) = true

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.onQueryChangedCommand("")
                return true
            }
        })

        return true
    }

    override fun onDestroy() {
        ItemListComponent.destroy()
        super.onDestroy()
    }
}