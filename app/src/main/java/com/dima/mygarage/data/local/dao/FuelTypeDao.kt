package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.mygarage.data.local.entity.FuelTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelTypeDao {

    @Query("SELECT * FROM fuel_types ORDER BY name ASC")
    fun observeFuelTypes(): Flow<List<FuelTypeEntity>>

    @Query("SELECT COUNT(*) FROM fuel_types")
    suspend fun getFuelTypesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFuelType(fuelType: FuelTypeEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFuelTypes(fuelTypes: List<FuelTypeEntity>)

    @Update
    suspend fun updateFuelType(fuelType: FuelTypeEntity)

    @Query("DELETE FROM fuel_types WHERE id = :id")
    suspend fun deleteFuelTypeById(id: Int)
}