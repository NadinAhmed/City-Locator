package com.nadin.city_locator.data.repository

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.nadin.city_locator.data.datasource.JsonCity
import com.nadin.city_locator.domain.model.City
import com.nadin.city_locator.domain.repository.CityRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepoImpl @Inject constructor(private val jsonCity: JsonCity) : CityRepo {
    private var allCities: List<City>? = null

    override suspend fun getAllCities(): List<City> {
        return if (allCities.isNullOrEmpty()) {
            return withContext(Dispatchers.IO) {
                val jsonString = jsonCity.readJsonFromAssets()
                val parsedCity = jsonCity.parseJsonToModel(jsonString)
                allCities = parsedCity.map { it.toDomainModel() }
                    .sortedBy { it.name.toLowerCase(Locale.current) }
                allCities!!
            }
        } else {
            allCities!!
        }
    }

    override suspend fun searchCities(searchQuery: String): List<City> {
        val result = mutableListOf<City>()
        withContext(Dispatchers.IO) {
            // We are going to use the binary search algorithm to get
            // lower time complexity of O(log(n))
            allCities?.let { cities ->
                var low = 0
                var high = cities.size - 1

                while (low <= high) {
                    val mid = (low + high) / 2
                    val midName = cities[mid].name.toLowerCase(Locale.current)
                    when {
                        midName.startsWith(searchQuery) -> {
                            result.add(cities[mid])
                            var left = mid - 1

                            while (left >= 0 && cities[left].name.toLowerCase(Locale.current).startsWith(searchQuery)
                            ) {
                                result.add(cities[left])
                                left--
                            }

                            var right = mid + 1
                            while (right < cities.size && cities[right].name.toLowerCase(Locale.current).startsWith(searchQuery)
                            ) {
                                result.add(cities[right])
                                right++
                            }
                            break
                        }

                        midName < searchQuery -> low = mid + 1
                        else -> high = mid - 1
                    }

                }
            }
        }
        return result
    }
}