package com.dima.mygarage.ui.garage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.garage.components.CarCard
import com.dima.mygarage.ui.garage.components.CarDetailsDialog
import com.dima.mygarage.ui.theme.MyGarageTheme
import java.math.BigDecimal

@Composable
fun GarageRoute(
    modifier: Modifier = Modifier,
    viewModel: GarageViewModel = hiltViewModel()
) {
    val cars by viewModel.cars.collectAsState()

    GarageScreen(
        cars = cars,
        modifier = modifier
    )
}

@Composable
fun GarageScreen(
    cars: List<Car>,
    modifier: Modifier = Modifier
) {
    var selectedCar by remember { mutableStateOf<Car?>(null) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 20.dp,
                end = 16.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                GarageHeader(
                    carsCount = cars.size
                )
            }

            items(
                items = cars,
                key = { car -> car.id }
            ) { car ->
                CarCard(
                    car = car,
                    onClick = {
                        selectedCar = car
                    }
                )
            }
        }

        selectedCar?.let { car ->
            CarDetailsDialog(
                car = car,
                onDismiss = {
                    selectedCar = null
                }
            )
        }
    }
}

@Composable
private fun GarageHeader(
    carsCount: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Garage",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "$carsCount cars in garage",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GarageScreenPreview() {
    MyGarageTheme {
        GarageScreen(
            cars = listOf(
                Car(
                    id = 1,
                    brand = "BMW",
                    model = "M340i",
                    year = 2019,
                    horsepower = 387,
                    price = BigDecimal("7000000"),
                    isFavorite = true
                ),
                Car(
                    id = 2,
                    brand = "Toyota",
                    model = "Supra",
                    year = 2021,
                    horsepower = 387,
                    price = BigDecimal("6500000"),
                    isFavorite = false
                )
            )
        )
    }
}