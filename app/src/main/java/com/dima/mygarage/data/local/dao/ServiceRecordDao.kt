package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.mygarage.data.local.entity.ServiceRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceRecordDao {

    @Query("SELECT * FROM service_records WHERE car_id = :carId ORDER BY date DESC, id DESC")
    fun observeServiceRecordsByCarId(carId: Int): Flow<List<ServiceRecordEntity>>

    @Query("SELECT * FROM service_records WHERE id = :id")
    fun observeServiceRecordById(id: Int): Flow<ServiceRecordEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceRecord(serviceRecord: ServiceRecordEntity): Long

    @Update
    suspend fun updateServiceRecord(serviceRecord: ServiceRecordEntity)

    @Query("DELETE FROM service_records WHERE id = :id")
    suspend fun deleteServiceRecordById(id: Int)

    @Query("DELETE FROM service_records WHERE car_id = :carId")
    suspend fun deleteServiceRecordsByCarId(carId: Int)

    @Query("SELECT COUNT(*) FROM service_records WHERE car_id = :carId")
    suspend fun getServiceRecordsCountByCarId(carId: Int): Int
}