package com.nadin.city_locator.data.repository

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.nadin.city_locator.data.datasource.JsonCity
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepoImpl @Inject constructor(private val jsonCity: JsonCity) : CityRepo {
    override suspend fun getAllCities(): List<City> {
        return withContext(Dispatchers.IO) {
            val jsonString = jsonCity.readJsonFromAssets()
            val parsedCity = jsonCity.parseJsonToModel(jsonString)
            parsedCity.map { it.toDomainModel() }.sortedBy { it.name.toLowerCase(Locale.current) }
        }
    }
}