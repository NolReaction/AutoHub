package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.mygarage.data.local.entity.FuelTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelTypeDao {

    @Query("SELECT * FROM fuel_types ORDER BY name ASC")
    fun observeFuelTypes(): Flow<List<FuelTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelType(fuelType: FuelTypeEntity): Long
}