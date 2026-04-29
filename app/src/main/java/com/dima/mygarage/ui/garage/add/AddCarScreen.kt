package com.dima.mygarage.ui.garage.add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dima.mygarage.R
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.garage.GarageViewModel
import java.math.BigDecimal

@Composable
fun AddCarRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GarageViewModel = hiltViewModel()
) {
    AddCarScreen(
        modifier = modifier,
        onBack = onBack,
        onSaveCar = { car ->
            viewModel.addCar(car)
            onBack()
        }
    )
}

@Composable
fun AddCarScreen(
    onBack: () -> Unit,
    onSaveCar: (Car) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }

    var brand by rememberSaveable { mutableStateOf("") }
    var model by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var horsepower by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    var showRequiredError by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.cancel)
                    )
                }

                Text(
                    text = stringResource(R.string.add_car),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedTextField(
                value = brand,
                onValueChange = {
                    brand = it
                    showRequiredError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.brand))
                },
                singleLine = true,
                isError = showRequiredError && brand.isBlank()
            )

            OutlinedTextField(
                value = model,
                onValueChange = {
                    model = it
                    showRequiredError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.model))
                },
                singleLine = true,
                isError = showRequiredError && model.isBlank()
            )

            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.year))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = horsepower,
                onValueChange = { horsepower = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.horsepower))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.price))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.favorite),
                    style = MaterialTheme.typography.titleMedium
                )

                Switch(
                    checked = isFavorite,
                    onCheckedChange = {
                        isFavorite = it
                    }
                )
            }

            if (showRequiredError) {
                Text(
                    text = stringResource(R.string.brand_and_model_required),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = onBack
                ) {
                    Text(stringResource(R.string.cancel))
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (brand.isBlank() || model.isBlank()) {
                            showRequiredError = true
                            return@Button
                        }

                        val car = Car(
                            id = 0,
                            brand = brand.trim(),
                            model = model.trim(),
                            year = year.toIntOrNull(),
                            horsepower = horsepower.toIntOrNull(),
                            price = price.toBigDecimalOrNullSafe(),
                            isFavorite = isFavorite
                        )

                        onSaveCar(car)
                    }
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    }
}

private fun String.toBigDecimalOrNullSafe(): BigDecimal? {
    val preparedValue = trim()
        .replace(',', '.')

    if (preparedValue.isBlank()) return null

    return runCatching {
        BigDecimal(preparedValue)
    }.getOrNull()
}