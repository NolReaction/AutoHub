package com.dima.mygarage.ui.garage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dima.mygarage.data.repository.CarRepository
import com.dima.mygarage.model.Car
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GarageViewModel(
    private val repository: CarRepository
) : ViewModel() {

    val cars: StateFlow<List<Car>> = repository.observeCars()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedCarsIfEmpty()
        }
    }
}

class GarageViewModelFactory(
    private val repository: CarRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GarageViewModel(repository) as T
    }
}