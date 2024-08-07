package com.nadin.city_locator.presentation.cityList

import com.nadin.city_locator.domain.model.City

sealed class CityListEvent {
    data class OnCityClicked(val city: City):CityListEvent()
}