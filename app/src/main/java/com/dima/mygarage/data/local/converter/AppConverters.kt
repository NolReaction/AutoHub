package com.dima.mygarage.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime


class AppConverters {
    @TypeConverter
    fun localDateTimeToString(value: LocalDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun stringToLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }
}