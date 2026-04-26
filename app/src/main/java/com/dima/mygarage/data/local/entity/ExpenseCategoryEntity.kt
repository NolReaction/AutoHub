package com.dima.mygarage.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "expense_categories",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class ExpenseCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String
)