package com.dima.mygarage.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Menu
import com.dima.mygarage.ui.garage.GarageRoute
import com.dima.mygarage.ui.menu.MenuScreen
import com.dima.mygarage.ui.theme.MyGarageTheme

private enum class MainTab {
    Garage,
    Menu
}

@Composable
fun MyGarageApp() {
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.Garage) }

    MyGarageTheme(
        darkTheme = isDarkTheme
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == MainTab.Garage,
                        onClick = {
                            selectedTab = MainTab.Garage
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.DirectionsCar,
                                contentDescription = "Garage"
                            )
                        },
                        label = {
                            Text("Garage")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedTab == MainTab.Menu,
                        onClick = {
                            selectedTab = MainTab.Menu
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "Menu"
                            )
                        },
                        label = {
                            Text("Menu")
                        }
                    )
                }
            }
        ) { innerPadding ->
            when (selectedTab) {
                MainTab.Garage -> {
                    GarageRoute(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                MainTab.Menu -> {
                    MenuScreen(
                        isDarkTheme = isDarkTheme,
                        onDarkThemeChange = {
                            isDarkTheme = it
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}