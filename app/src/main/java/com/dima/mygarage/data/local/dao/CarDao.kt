package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.mygarage.data.local.entity.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM cars ORDER BY id DESC")
    fun observeCars(): Flow<List<CarEntity>>

    @Query("SELECT * FROM cars WHERE ID = :id")
    fun observeCarById(id: Int): Flow<CarEntity?>

    @Query("SELECT COUNT(*) FROM cars")
    suspend fun getCarsCount (): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: CarEntity): Long
}
