package com.dima.mygarage.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(
    tableName = "fuel_records",
    foreignKeys = [
        ForeignKey(
            entity = CarEntity::class,
            parentColumns = ["id"],
            childColumns = ["car_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FuelTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["fuel_type_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["car_id"]),
        Index(value = ["fuel_type_id"])
    ]
)
data class FuelRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "car_id")
    val carId: Int,

    val date: LocalDateTime,

    val liters: Double,

    @ColumnInfo(name = "fuel_type_id")
    val fuelTypeId: Int,

    @ColumnInfo(name = "price_rub")
    val priceRub: Int,

    @ColumnInfo(name = "price_per_liter_rub")
    val pricePerLiterRub: Int? = null,

    val note: String? = null
)