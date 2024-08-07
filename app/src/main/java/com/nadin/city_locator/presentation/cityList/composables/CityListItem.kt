package com.nadin.city_locator.presentation.cityList.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.model.Coordinates
import com.nadin.city_locator.presentation.theme.CityLocatorTheme

@Composable
fun CityListItem(city: City, onClick: () -> Unit, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .padding(4.dp)
    ) {
        Card(
            onClick = onClick,
            shape = RoundedCornerShape(size = 6.dp),
            modifier = modifier
                .fillMaxWidth()

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "${city.name}, ${city.country}",
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = "Lon: ",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W600,
                    )
                    Text(
                        text = "${city.coordinates.lon}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W400,
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Lat: ",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W600,
                    )
                    Text(
                        text = "${city.coordinates.lat}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W400,
                    )
                }

            }

        }
    }

}

@Preview
@Composable
private fun CityListItemPreview() {
    CityLocatorTheme {
        CityListItem(
            City(
                707860,
                "Hurzuf",
                "UA",
                Coordinates(34.283333, 44.549999),
            ), {}, modifier = Modifier.height(70.dp)
        )
    }
}
