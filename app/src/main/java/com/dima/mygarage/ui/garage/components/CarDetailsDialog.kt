package com.dima.mygarage.ui.garage.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.dima.mygarage.R
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.common.formatter.formatRubPrice

@Composable
fun CarDetailsDialog(
    car: Car,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val unknown = stringResource(R.string.unknown)
    val yearLabel = stringResource(R.string.year)
    val horsepowerLabel = stringResource(R.string.horsepower)
    val horsepowerShort = stringResource(R.string.horsepower_short)
    val priceLabel = stringResource(R.string.price)

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
                text = buildCarDetailsText(
                    car = car,
                    unknown = unknown,
                    yearLabel = yearLabel,
                    horsepowerLabel = horsepowerLabel,
                    horsepowerShort = horsepowerShort,
                    priceLabel = priceLabel
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.close))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDeleteConfirmation = true
                }
            ) {
                Text(
                    text = stringResource(R.string.delete),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = {
                showDeleteConfirmation = false
            },
            title = {
                Text(
                    text = stringResource(R.string.delete_confirmation_title),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.delete_confirmation_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        onDelete()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.confirm_delete),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

private fun buildCarDetailsText(
    car: Car,
    unknown: String,
    yearLabel: String,
    horsepowerLabel: String,
    horsepowerShort: String,
    priceLabel: String
): String {
    return """
        $yearLabel: ${car.year ?: unknown}
        $horsepowerLabel: ${car.horsepower?.let { "$it $horsepowerShort" } ?: unknown}
        $priceLabel: ${car.price?.let { formatRubPrice(it) } ?: unknown}
    """.trimIndent()
}