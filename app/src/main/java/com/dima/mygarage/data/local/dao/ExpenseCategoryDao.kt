package com.dima.mygarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dima.mygarage.data.local.entity.ExpenseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {

    @Query("SELECT * FROM expense_categories ORDER BY name ASC")
    fun observeExpenseCategories(): Flow<List<ExpenseCategoryEntity>>

    @Query("SELECT COUNT(*) FROM expense_categories")
    suspend fun getExpenseCategoriesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpenseCategory(category: ExpenseCategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpenseCategories(categories: List<ExpenseCategoryEntity>)

    @Update
    suspend fun updateExpenseCategory(category: ExpenseCategoryEntity)

    @Query("DELETE FROM expense_categories WHERE id = :id")
    suspend fun deleteExpenseCategoryById(id: Int)
}