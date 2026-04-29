package com.dima.mygarage.ui.garage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.common.formatter.formatRubPrice
import com.dima.mygarage.ui.theme.MyGarageTheme
import java.math.BigDecimal

@Composable
fun CarCard(
    car: Car,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DirectionsCar,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Column {
                        Text(
                            text = "${car.brand} ${car.model}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = buildCarSubtitle(car),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                        )
                    }
                }

                if (car.isFavorite) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            car.price?.let { price ->
                Text(
                    text = formatRubPrice(price),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private fun buildCarSubtitle(car: Car): String {
    val parts = buildList {
        car.year?.let { add(it.toString()) }
        car.horsepower?.let { add("$it hp") }
    }

    return if (parts.isEmpty()) {
        "No details"
    } else {
        parts.joinToString(separator = " • ")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F8FB)
@Composable
fun CarCardPreview() {
    MyGarageTheme {
        CarCard(
            car = Car(
                id = 0,
                brand = "BMW",
                model = "M340i",
                year = 2019,
                horsepower = 387,
                price = BigDecimal("7000000"),
                isFavorite = true
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun CarCardDarkPreview() {
    MyGarageTheme(
        darkTheme = true
    ) {
        CarCard(
            car = Car(
                id = 0,
                brand = "BMW",
                model = "M340i",
                year = 2019,
                horsepower = 387,
                price = BigDecimal("7000000"),
                isFavorite = true
            ),
            onClick = {}
        )
    }
}