package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.mygarage.data.local.entity.ServiceRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceRecordDao {

    @Query("SELECT * FROM service_records WHERE car_id = :carId ORDER BY date DESC")
    fun observeServiceRecordsByCarId(carId: Int): Flow<List<ServiceRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceRecord(record: ServiceRecordEntity): Long

    @Delete
    suspend fun deleteServiceRecord(record: ServiceRecordEntity)

    @Query("DELETE FROM service_records WHERE id = :id")
    suspend fun deleteServiceRecordById(id: Int)
}