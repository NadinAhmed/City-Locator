package com.nadin.city_locator.data.datasource.dto

import com.google.gson.annotations.SerializedName
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.model.Coordinates

data class CityDto(
    @SerializedName("_id")
    val id: Int,
    val name: String,
    val country: String,
    @SerializedName("coord")
    val coordinates: List<Coordinates>,
) {
    fun toDomainModel() = City(
        id = id,
        name = name,
        country = country,
        coordinates = coordinates
    )

    constructor(city: City) : this(
        id = city.id,
        name = city.name,
        country = city.country,
        coordinates = city.coordinates,
    )
}
