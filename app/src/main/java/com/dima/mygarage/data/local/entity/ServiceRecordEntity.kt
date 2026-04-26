package com.dima.mygarage.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "service_records",
    foreignKeys = [
        ForeignKey(
            entity = CarEntity::class,
            parentColumns = ["id"],
            childColumns = ["car_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["car_id"])
    ]
)
data class ServiceRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "car_id")
    val carId: Int,

    val date: LocalDateTime,

    val mileage: Int? = null,

    val title: String? = null,

    @ColumnInfo(name = "price_rub")
    val priceRub: Int,

    val note: String? = null
)