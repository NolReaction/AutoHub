package com.dima.mygarage.ui.garage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "MyGarage",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(12.dp))

        for (car in cars) {
            CarCard(
                car = car,
                onClick = {
                    selectedCar = car
                }
            )

            Spacer(Modifier.height(12.dp))
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