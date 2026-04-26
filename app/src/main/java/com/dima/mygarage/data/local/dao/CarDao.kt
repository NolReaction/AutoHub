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

    @Query("SELECT * FROM cars WHERE id = :id")
    fun observeCarById(id: Int): Flow<CarEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: CarEntity): Long

    @Update
    suspend fun updateCar(car: CarEntity)

    @Delete
    suspend fun deleteCar(car: CarEntity)

    @Query("DELETE FROM cars WHERE id = :id")
    suspend fun deleteCarById(id: Int)

    @Query("SELECT COUNT(*) FROM cars")
    suspend fun getCarsCount(): Int
}