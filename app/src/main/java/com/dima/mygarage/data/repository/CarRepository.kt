package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.entity.CarEntity
import com.dima.mygarage.model.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class CarRepository(
    private val carDao: CarDao
) {
    fun observeCars(): Flow<List<Car>> {
        return carDao.observeCars().map { entities ->
            entities.map { it.toCar() }
        }
    }

    suspend fun seedCarsIfEmpty() {
        if (carDao.getCarsCount() > 0) return

        carDao.insertCar(
            CarEntity(
                brand = "BMW",
                model = "M340i",
                engineType = "Petrol",
                year = 2019,
                mileage = 80_000,
                priceRub = 7_000_000,
                vin = "BMW-M340I-DEMO",
                isFavorite = false
            )
        )

        carDao.insertCar(
            CarEntity(
                brand = "Toyota",
                model = "Supra",
                engineType = "Petrol",
                year = 2021,
                mileage = 40_000,
                priceRub = 6_500_000,
                vin = "TOYOTA-SUPRA-DEMO",
                isFavorite = false
            )
        )
    }
}

private fun CarEntity.toCar(): Car {
    return Car(
        id = id,
        brand = brand,
        model = model,
        year = year,
        horsepower = null,
        price = priceRub?.let { BigDecimal(it) },
        isFavorite = isFavorite,
        image = null
    )
}