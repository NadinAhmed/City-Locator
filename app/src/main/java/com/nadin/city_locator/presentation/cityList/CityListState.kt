package com.nadin.city_locator.presentation.cityList

import com.nadin.city_locator.domain.model.City

data class CityListState(
    val cities: List<City> = emptyList(),
    val clickedIndex: Int? = null,
    val isLoading: Boolean = false,
)