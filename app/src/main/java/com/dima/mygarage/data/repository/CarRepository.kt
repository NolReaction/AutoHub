package com.dima.mygarage.data.repository

import com.dima.mygarage.data.local.dao.CarDao
import com.dima.mygarage.data.local.entity.CarEntity
import com.dima.mygarage.model.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class CarRepository(private val carDao: CarDao) {
    fun observeCars(): Flow<List<Car>> {
        return carDao.observeCars().map { entities ->
                entities.map { it.toCar() }
            }
    }

    suspend fun insertCar(car: Car): Long {
        return carDao.insertCar(car.toEntity())
    }

    suspend fun seedCarsIfEmpty() {
        val carCount = carDao.getCarsCount()

        if (carCount > 0) return

        insertCar(
            Car(
                id = 0,
                brand = "BMW",
                model = "M340i",
                year = 2019,
                horsepower = 387,
                price = BigDecimal("7000000"),
                isFavorite = false
            )
        )

        insertCar(
            Car(
                id = 0,
                brand = "Toyota",
                model = "Supra",
                year = 2021,
                horsepower = 387,
                price = BigDecimal("6500000"),
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

private fun Car.toEntity(): CarEntity {
    return CarEntity(
        id = id,
        brand = brand,
        model = model,
        year = year,
        priceRub = price?.toInt(),
        isFavorite = isFavorite
    )
}