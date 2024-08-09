package com.nadin.city_locator.presentation.cityList

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadin.city_locator.domain.usecase.GetAllCitiesUseCase
import com.nadin.city_locator.domain.usecase.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CityListState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            val cities = getAllCitiesUseCase()
            _state.update {
                it.copy(
                    cities = cities,
                    isLoading = false,
                )
            }
        }
    }

    fun onEvent(event: CityListEvent){
        when(event){
            is CityListEvent.OnCityClicked-> {
                val intentUri =
                    Uri.parse("geo:${event.city.coordinates.lat},${event.city.coordinates.lon}")
                val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                _state.update {
                    it.copy(
                        intent = mapIntent,
                    )
                }
            }

            CityListEvent.OnSearchClicked -> {
                _state.update {
                    it.copy(
                        isSearchVisible = true,
                    )
                }
            }
            CityListEvent.OnSearchHideClicked -> {
                viewModelScope.launch {
                    val cities = getAllCitiesUseCase()
                    _state.update {
                        it.copy(
                            cities = cities,
                            isSearchVisible = false,
                            searchQuery = "",
                        )
                    }
                }
            }
            is CityListEvent.OnSearchValueChanged -> {
                searchJob?.cancel()

                _state.update {
                    it.copy(
                        searchQuery = event.searchQuery
                    )
                }

                searchJob = viewModelScope.launch {
                    val results = searchCitiesUseCase(_state.value.searchQuery)
                    _state.update {
                        it.copy(
                            cities = results
                        )
                    }
                }
            }
        }
    }

    fun resetIntent() {
        _state.update {
            it.copy(
                intent = null
            )
        }
    }
}