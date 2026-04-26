package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.mygarage.data.local.entity.FuelRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelRecordDao {

    @Query("SELECT * FROM fuel_records WHERE car_id = :carId ORDER BY date DESC")
    fun observeFuelRecordsByCarId(carId: Int): Flow<List<FuelRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelRecord(record: FuelRecordEntity): Long

    @Delete
    suspend fun deleteFuelRecord(record: FuelRecordEntity)

    @Query("DELETE FROM fuel_records WHERE id = :id")
    suspend fun deleteFuelRecordById(id: Int)
}