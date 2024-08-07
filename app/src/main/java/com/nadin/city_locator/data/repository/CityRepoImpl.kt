package com.nadin.city_locator.data.repository

import com.nadin.city_locator.data.datasource.JsonCity
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import javax.inject.Inject

class CityRepoImpl @Inject constructor(private val jsonCity: JsonCity):CityRepo {
    override fun getAllCities(): List<City> {
        val jsonString = jsonCity.readJsonFromAssets()
        return jsonCity.parseJsonToModel(jsonString)
    }
}