package com.dima.mygarage.ui.navigation

sealed class AppRoute(
    val route: String
) {
    data object Garage : AppRoute("garage")
    data object AddCar : AppRoute("add_car")
    data object Menu : AppRoute("menu")

    data object EditCar : AppRoute("edit_car/{carId}") {
        const val CAR_ID_ARG = "carId"

        fun createRoute(carId: Int): String {
            return "edit_car/$carId"
        }
    }
}