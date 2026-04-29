package com.dima.mygarage.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dima.mygarage.R
import com.dima.mygarage.ui.garage.GarageRoute
import com.dima.mygarage.ui.garage.add.AddCarRoute
import com.dima.mygarage.ui.garage.add.EditCarRoute
import com.dima.mygarage.ui.menu.MenuScreen
import com.dima.mygarage.ui.navigation.AppRoute
import com.dima.mygarage.ui.theme.MyGarageTheme

@Composable
fun MyGarageApp(
    appViewModel: AppViewModel = hiltViewModel()
) {
    val settings by appViewModel.settings.collectAsState()
    val navController = rememberNavController()

    MyGarageTheme(
        darkTheme = settings.isDarkTheme
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBar {
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == AppRoute.Garage.route
                        } == true,
                        onClick = {
                            navController.navigate(AppRoute.Garage.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.DirectionsCar,
                                contentDescription = stringResource(R.string.tab_garage)
                            )
                        },
                        label = {
                            Text(stringResource(R.string.tab_garage))
                        }
                    )

                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == AppRoute.Menu.route
                        } == true,
                        onClick = {
                            navController.navigate(AppRoute.Menu.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = stringResource(R.string.tab_menu)
                            )
                        },
                        label = {
                            Text(stringResource(R.string.tab_menu))
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppRoute.Garage.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(AppRoute.Garage.route) {
                    GarageRoute(
                        onAddCarClick = {
                            navController.navigate(AppRoute.AddCar.route)
                        },
                        onEditCarClick = { carId ->
                            navController.navigate(AppRoute.EditCar.createRoute(carId))
                        }
                    )
                }

                composable(AppRoute.AddCar.route) {
                    AddCarRoute(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    route = AppRoute.EditCar.route,
                    arguments = listOf(
                        navArgument(AppRoute.EditCar.CAR_ID_ARG) {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val carId = backStackEntry.arguments?.getInt(AppRoute.EditCar.CAR_ID_ARG)

                    if (carId != null) {
                        EditCarRoute(
                            carId = carId,
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

                composable(AppRoute.Menu.route) {
                    MenuScreen(
                        isDarkTheme = settings.isDarkTheme,
                        onDarkThemeChange = appViewModel::setDarkTheme
                    )
                }
            }
        }
    }
}