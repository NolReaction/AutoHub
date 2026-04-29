package com.dima.mygarage.ui.garage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dima.mygarage.data.repository.CarRepository
import com.dima.mygarage.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    val cars: StateFlow<List<Car>> = carRepository.observeCars()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            carRepository.seedCarsIfEmpty()
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch {
            carRepository.insertCar(car)
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch {
            carRepository.updateCar(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch {
            carRepository.deleteCarById(car.id)
        }
    }

    fun toggleFavorite(car: Car) {
        viewModelScope.launch {
            carRepository.setCarFavorite(
                carId = car.id,
                isFavorite = !car.isFavorite
            )
        }
    }
}