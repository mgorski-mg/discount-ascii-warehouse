package com.mgorski.discountasciiwarehouse.model

import org.joda.money.CurrencyUnit
import org.joda.money.Money

class AsciiItem(price: Int, val face: String, val stock: Int, val tags: List<String>) {
    val priceString: String

    init {
        val money = Money.of(CurrencyUnit.USD, price / 100.0)
        priceString = money.currencyUnit.symbol + money.amount
    }
}