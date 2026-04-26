package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.entity.CarEntity
import com.dima.mygarage.model.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class CarRepository(
)