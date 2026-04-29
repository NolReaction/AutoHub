package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.ExpenseCategoryDao
import com.dima.mygarage.data.local.dao.ExpenseDao
import com.dima.mygarage.data.local.entity.ExpenseCategoryEntity
import com.dima.mygarage.data.local.entity.ExpenseEntity
import com.dima.mygarage.data.seed.defaultExpenseCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val expenseCategoryDao: ExpenseCategoryDao
) {
    fun observeExpensesByCarId(carId: Int): Flow<List<ExpenseEntity>> {
        return expenseDao.observeExpensesByCarId(carId)
    }

    fun observeExpenseCategories(): Flow<List<ExpenseCategoryEntity>> {
        return expenseCategoryDao.observeExpenseCategories()
    }

    suspend fun insertExpense(expense: ExpenseEntity): Long {
        return expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpenseById(id: Int) {
        expenseDao.deleteExpenseById(id)
    }

    suspend fun seedExpenseCategoriesIfEmpty() {
        val count = expenseCategoryDao.getExpenseCategoriesCount()

        if (count > 0) return

        val categories = defaultExpenseCategories.map { name ->
            ExpenseCategoryEntity(name = name)
        }

        expenseCategoryDao.insertExpenseCategories(categories)
    }
}