package com.dima.mygarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dima.mygarage.data.local.MyGarageDatabase
import com.dima.mygarage.data.repository.CarRepository
import com.dima.mygarage.ui.MyGarageApp
import com.dima.mygarage.ui.garage.GarageViewModelFactory
import com.dima.mygarage.ui.theme.MyGarageTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MyGarageDatabase.getInstance(applicationContext)
        val repository = CarRepository(database.carDao())
        val garageViewModelFactory = GarageViewModelFactory(repository)

        enableEdgeToEdge()

        setContent {
            MyGarageTheme {
                Scaffold { innerPadding ->
                    MyGarageApp(
                        modifier = Modifier.padding(innerPadding),
                        garageViewModelFactory = garageViewModelFactory
                    )
                }
            }
        }
    }
}