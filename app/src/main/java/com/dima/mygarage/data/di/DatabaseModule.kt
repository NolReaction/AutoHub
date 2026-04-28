package com.dima.mygarage.data.di

import android.content.Context
import com.dima.mygarage.data.local.MyGarageDatabase
import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.dao.ExpenseCategoryDao
import com.dima.mygarage.data.local.dao.ExpensesDao
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
    fun provideExpensesDao(database: MyGarageDatabase): ExpensesDao {
        return database.expensesDao()
    }

    @Provides
    fun provideExpenseCategoryDao(database: MyGarageDatabase): ExpenseCategoryDao {
        return database.expenseCategoryDao()
    }
}