package com.dima.mygarage.ui.garage.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.common.formatter.formatRubPrice

@Composable
fun CarDetailsDialog(
    car: Car,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "${car.brand} ${car.model}",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(
                text = buildCarDetailsText(car),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Close")
            }
        }
    )
}

private fun buildCarDetailsText(car: Car): String {
    return """
        Year: ${car.year ?: "Unknown"}
        Horsepower: ${car.horsepower?.let { "$it hp" } ?: "Unknown"}
        Price: ${car.price?.let { formatRubPrice(it) } ?: "Unknown"}
    """.trimIndent()
}