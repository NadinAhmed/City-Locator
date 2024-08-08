package com.nadin.city_locator.domain.usecase

import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import javax.inject.Inject

class SortCitiesUseCase @Inject constructor(private val cityRepo: CityRepo) {
    operator fun invoke(cityItems:List<City>):List<City> = cityRepo.sortCityList(cityItems)
}