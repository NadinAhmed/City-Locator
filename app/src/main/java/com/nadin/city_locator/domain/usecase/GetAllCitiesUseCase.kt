package com.nadin.city_locator.domain.usecase

import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(private val cityRepo: CityRepo) {
    operator fun invoke(): List<City> = cityRepo.getAllCities()
}