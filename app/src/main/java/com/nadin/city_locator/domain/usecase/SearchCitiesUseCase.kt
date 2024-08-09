package com.nadin.city_locator.domain.usecase

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(private val cityRepo: CityRepo) {
    suspend operator fun invoke(searchQuery: String): List<City> {
        return if (searchQuery.isBlank()) {
            cityRepo.getAllCities()
        } else {
            cityRepo.searchCities(searchQuery.trim().toLowerCase(Locale.current))
        }
    }
}