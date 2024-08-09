package com.nadin.city_locator.presentation.cityList

import android.content.Intent
import com.nadin.city_locator.domain.model.City

data class CityListState(
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val intent: Intent? = null,
)