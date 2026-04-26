package com.dima.mygarage.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dima.mygarage.data.local.converter.AppConverters
import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.entity.CarEntity

@Database(
    entities = [
        CarEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(AppConverters::class)
abstract class MyGarageDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

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