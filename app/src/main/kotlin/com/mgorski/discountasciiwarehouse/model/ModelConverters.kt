package com.mgorski.discountasciiwarehouse.model

import com.mgorski.discountasciiwarehouse.network.model.WebAsciiItem

fun WebAsciiItem.toAsciiItem() = AsciiItem(price, face, stock, tags)