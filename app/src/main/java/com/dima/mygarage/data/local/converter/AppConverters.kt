package com.dima.mygarage.data.local.converter

import androidx.room.TypeConverter
import java.math.BigDecimal
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

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    @TypeConverter
    fun stringToBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}