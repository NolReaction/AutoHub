package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.FuelRecordDao
import com.dima.mygarage.data.local.dao.FuelTypeDao
import com.dima.mygarage.data.local.entity.FuelRecordEntity
import com.dima.mygarage.data.local.entity.FuelTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FuelRepository @Inject constructor(
    private val fuelRecordDao: FuelRecordDao,
    private val fuelTypeDao: FuelTypeDao
) {
    fun observeFuelRecordsByCarId(carId: Int): Flow<List<FuelRecordEntity>> {
        return fuelRecordDao.observeFuelRecordsByCarId(carId)
    }

    fun observeFuelTypes(): Flow<List<FuelTypeEntity>> {
        return fuelTypeDao.observeFuelTypes()
    }

    suspend fun insertFuelRecord(fuelRecord: FuelRecordEntity): Long {
        return fuelRecordDao.insertFuelRecord(fuelRecord)
    }

    suspend fun deleteFuelRecordById(id: Int) {
        fuelRecordDao.deleteFuelRecordById(id)
    }

    suspend fun seedFuelTypesIfEmpty() {
        val count = fuelTypeDao.getFuelTypesCount()

        if (count > 0) return

        val fuelTypes = listOf(
            FuelTypeEntity(name = "Бензин"),
            FuelTypeEntity(name = "Дизель"),
            FuelTypeEntity(name = "Газ"),
            FuelTypeEntity(name = "Электро")
        )

        fuelTypeDao.insertFuelTypes(fuelTypes)
    }
}