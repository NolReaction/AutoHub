package com.dima.mygarage.ui.garage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dima.mygarage.model.Car
import com.dima.mygarage.ui.theme.MyGarageTheme
import java.math.BigDecimal

@Composable
fun CarCard(
    car: Car, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = """
            Car brand: ${car.brand}
            Car model: ${car.model}
        """.trimIndent()
            )
        }
    }
}

@Preview(showBackground = true)
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
                price = BigDecimal("7000000")
            ),
            onClick = {}
        )
    }
}