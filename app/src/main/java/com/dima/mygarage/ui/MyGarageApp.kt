package com.dima.mygarage.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dima.mygarage.ui.garage.GarageRoute

@Composable
fun MyGarageApp(
    modifier: Modifier = Modifier
) {
    GarageRoute(
        modifier = modifier
    )
}