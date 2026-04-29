package com.dima.mygarage.ui.common.formatter

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun formatRubPrice(price: BigDecimal): String {
    val formatter = NumberFormat.getIntegerInstance(Locale("ru", "RU"))
    return "${formatter.format(price)} ₽"
}