package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.ServiceRecordDao
import com.dima.mygarage.data.local.entity.ServiceRecordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceRepository @Inject constructor(
    private val serviceRecordDao: ServiceRecordDao
) {
    fun observeServiceRecordsByCarId(carId: Int): Flow<List<ServiceRecordEntity>> {
        return serviceRecordDao.observeServiceRecordsByCarId(carId)
    }

    suspend fun insertServiceRecord(serviceRecord: ServiceRecordEntity): Long {
        return serviceRecordDao.insertServiceRecord(serviceRecord)
    }

    suspend fun deleteServiceRecordById(id: Int) {
        serviceRecordDao.deleteServiceRecordById(id)
    }
}