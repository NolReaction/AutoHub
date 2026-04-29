package com.dima.mygarage.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dima.mygarage.data.local.converter.AppConverters
import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.dao.ExpenseCategoryDao
import com.dima.mygarage.data.local.dao.ExpenseDao
import com.dima.mygarage.data.local.dao.FuelRecordDao
import com.dima.mygarage.data.local.dao.FuelTypeDao
import com.dima.mygarage.data.local.dao.ServiceRecordDao
import com.dima.mygarage.data.local.entity.CarEntity
import com.dima.mygarage.data.local.entity.ExpenseCategoryEntity
import com.dima.mygarage.data.local.entity.ExpenseEntity
import com.dima.mygarage.data.local.entity.FuelRecordEntity
import com.dima.mygarage.data.local.entity.FuelTypeEntity
import com.dima.mygarage.data.local.entity.ServiceRecordEntity

@Database(
    entities = [
        CarEntity::class,
        ExpenseCategoryEntity::class,
        ExpenseEntity::class,
        FuelTypeEntity::class,
        FuelRecordEntity::class,
        ServiceRecordEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(AppConverters::class)
abstract class MyGarageDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun fuelTypeDao(): FuelTypeDao
    abstract fun fuelRecordDao(): FuelRecordDao
    abstract fun serviceRecordDao(): ServiceRecordDao

    companion object {
        @Volatile
        private var INSTANCE: MyGarageDatabase? = null

        fun getInstance(context: Context): MyGarageDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MyGarageDatabase::class.java,
                    "my_garage.db"
                ).build().also { database ->
                    INSTANCE = database
                }
            }
        }
    }
}
