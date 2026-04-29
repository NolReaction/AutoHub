package com.dima.mygarage.data.di

import android.content.Context
import com.dima.mygarage.data.local.MyGarageDatabase
import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.dao.ExpenseCategoryDao
import com.dima.mygarage.data.local.dao.ExpenseDao
import com.dima.mygarage.data.local.dao.FuelRecordDao
import com.dima.mygarage.data.local.dao.FuelTypeDao
import com.dima.mygarage.data.local.dao.ServiceRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyGarageDatabase {
        return MyGarageDatabase.getInstance(context)
    }

    @Provides
    fun provideCarDao(database: MyGarageDatabase): CarDao {
        return database.carDao()
    }

    @Provides
    fun provideExpenseDao(database: MyGarageDatabase): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    fun provideExpenseCategoryDao(database: MyGarageDatabase): ExpenseCategoryDao {
        return database.expenseCategoryDao()
    }

    @Provides
    fun provideFuelTypeDao(database: MyGarageDatabase): FuelTypeDao {
        return database.fuelTypeDao()
    }

    @Provides
    fun provideFuelRecordDao(database: MyGarageDatabase): FuelRecordDao {
        return database.fuelRecordDao()
    }

    @Provides
    fun provideServiceRecordDao(database: MyGarageDatabase): ServiceRecordDao {
        return database.serviceRecordDao()
    }
}