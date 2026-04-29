package com.dima.mygarage.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cars",
    indices = [
        Index(value = ["vin"], unique = true)
    ]
)
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val brand: String,
    val model: String,

    @ColumnInfo(name = "engine_type")
    val engineType: String? = null,

    val horsepower: Int? = null,

    val year: Int? = null,
    val mileage: Int? = null,

    @ColumnInfo(name = "price_rub")
    val priceRub: Int? = null,

    val vin: String? = null,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)