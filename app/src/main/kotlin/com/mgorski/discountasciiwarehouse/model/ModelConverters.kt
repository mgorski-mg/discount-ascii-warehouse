package com.mgorski.discountasciiwarehouse.model

import com.mgorski.discountasciiwarehouse.network.model.WebAsciiItem

fun WebAsciiItem.toAsciiItem() = AsciiItem("$%.2f".format(price / 100.0), face, stock, tags)