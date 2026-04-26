package com.dima.mygarage.ui.garage

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.dima.mygarage.model.Car

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsDialog(
    car: Car,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("${car.brand} ${car.model}")
        },

        text = {
            Text(
                text = """
                    Year: ${car.year}
                    Horsepower: ${car.horsepower}
                    Price: ${car.price}
                """.trimIndent()
            )
        },

        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }

    )
}