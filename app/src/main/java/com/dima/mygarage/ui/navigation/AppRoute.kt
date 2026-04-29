package com.dima.mygarage.ui.navigation

sealed class AppRoute(
    val route: String
) {
    data object Garage : AppRoute("garage")
    data object AddCar : AppRoute("add_car")
    data object Menu : AppRoute("menu")
}