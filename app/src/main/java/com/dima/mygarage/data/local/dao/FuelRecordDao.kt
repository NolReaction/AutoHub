package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.mygarage.data.local.entity.FuelRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelRecordDao {

    @Query("SELECT * FROM fuel_records WHERE car_id = :carId ORDER BY date DESC, id DESC")
    fun observeFuelRecordsByCarId(carId: Int): Flow<List<FuelRecordEntity>>

    @Query("SELECT * FROM fuel_records WHERE id = :id")
    fun observeFuelRecordById(id: Int): Flow<FuelRecordEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelRecord(fuelRecord: FuelRecordEntity): Long

    @Update
    suspend fun updateFuelRecord(fuelRecord: FuelRecordEntity)

    @Query("DELETE FROM fuel_records WHERE id = :id")
    suspend fun deleteFuelRecordById(id: Int)

    @Query("DELETE FROM fuel_records WHERE car_id = :carId")
    suspend fun deleteFuelRecordsByCarId(carId: Int)

    @Query("SELECT COUNT(*) FROM fuel_records WHERE car_id = :carId")
    suspend fun getFuelRecordsCountByCarId(carId: Int): Int
}