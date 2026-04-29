package com.dima.mygarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dima.mygarage.ui.MyGarageApp
import com.dima.mygarage.ui.theme.MyGarageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyGarageTheme {
                MyGarageApp()
            }
        }
    }
}