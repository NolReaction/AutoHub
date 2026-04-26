package com.dima.mygarage.model

import java.math.BigDecimal

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val year: Int?,
    val horsepower: Int?,
    val price: BigDecimal?,
    val isFavorite: Boolean = false,
    val image: Int? = null
)