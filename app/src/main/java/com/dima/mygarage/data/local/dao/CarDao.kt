package com.dima.mygarage.data.local.dao

import androidx.room.Dao
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

    @Query("SELECT COUNT(*) FROM cars")
    suspend fun getCarsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: CarEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(cars: List<CarEntity>)

    @Update
    suspend fun updateCar(car: CarEntity)

    @Query("UPDATE cars SET is_favorite = :isFavorite WHERE id = :carId")
    suspend fun updateFavoriteStatus(
        carId: Int,
        isFavorite: Boolean
    )

    @Query("DELETE FROM cars WHERE id = :id")
    suspend fun deleteCarById(id: Int)
}