package com.dima.mygarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                Scaffold { innerPadding ->
                    MyGarageApp(
                        modifier = Modifier.padding(innerPadding)
                        )
                }
            }
        }
    }
}