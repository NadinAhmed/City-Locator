package com.nadin.city_locator.domain.model

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val coordinates: List<Coordinates>,
)

data class Coordinates(
    val lon: Double,
    val lat: Double,
)

