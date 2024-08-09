package com.nadin.city_locator.domain.repository

import com.nadin.city_locator.domain.model.City

interface CityRepo {
    suspend fun getAllCities():List<City>
}