package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dima.mygarage.data.local.entity.ExpenseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {
    @Query("SELECT * FROM expense_categories ORDER BY name ASC")
    fun observeExpenseCategories(): Flow<List<ExpenseCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenseCategory(expenseCategory: ExpenseCategoryEntity): Long

    @Query("DELETE FROM expense_categories WHERE id = :id")
    suspend fun deleteExpenseCategoryById(id: Int)

    // Это понадобится для стартовых категорий: если категорий 0 - добавить стандартные
    @Query("SELECT COUNT(*) FROM expense_categories")
    suspend fun getCategoriesCount(): Int
}