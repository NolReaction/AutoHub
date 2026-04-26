package com.dima.mygarage.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dima.mygarage.ui.garage.GarageRoute
import com.dima.mygarage.ui.garage.GarageViewModel
import com.dima.mygarage.ui.garage.GarageViewModelFactory

@Composable
fun MyGarageApp(
    modifier: Modifier = Modifier,
    garageViewModelFactory: GarageViewModelFactory
) {
    val viewModel: GarageViewModel = viewModel(
        factory = garageViewModelFactory
    )

    GarageRoute(
        viewModel = viewModel,
        modifier = modifier
    )
}