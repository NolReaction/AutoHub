package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.mygarage.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {
    // Наблюдать все расходы конкретной машины
    @Query("SELECT * FROM expenses WHERE car_id = :carId ORDER BY date DESC")
    fun observeExpensesByCarId(carId: Int): Flow<List<ExpenseEntity>>

    // Добавить новый расход
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    // Удалить расход по id
    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteExpenseById(id: Int)
}
