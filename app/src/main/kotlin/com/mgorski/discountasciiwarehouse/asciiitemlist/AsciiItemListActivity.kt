package com.mgorski.discountasciiwarehouse.asciiitemlist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mgorski.discountasciiwarehouse.R
import com.mgorski.discountasciiwarehouse.databinding.ActivityAsciiItemListBinding
import com.mgorski.discountasciiwarehouse.di.AppComponent
import com.mgorski.discountasciiwarehouse.model.AsciiItem
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService
import rx.schedulers.Schedulers
import javax.inject.Inject

class AsciiItemListActivity : AppCompatActivity() {

    @Inject
    lateinit var service: AsciiWarehouseService

    private val viewModel by lazy { AsciiItemListViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAsciiItemListBinding>(this, R.layout.activity_ascii_item_list).viewModel = viewModel

        AppComponent.instance.inject(this)

        service.getAsciiItems()
                .subscribeOn(Schedulers.io())
                .map { it.map { AsciiItem(it.price, it.face, it.stock, it.tags) } }
                .subscribe {
                    viewModel.items.addAll(it)
                }
    }
}