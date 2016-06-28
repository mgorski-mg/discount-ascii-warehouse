package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.databinding.ActivityAsciiItemListBinding
import com.mgorski.discountasciiwarehouse.di.AppComponent
import javax.inject.Inject

class AsciiItemListActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: AsciiItemListViewModel

    init {
        AppComponent.instance.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAsciiItemListBinding>(this, R.layout.activity_ascii_item_list).viewModel = viewModel
    }
}