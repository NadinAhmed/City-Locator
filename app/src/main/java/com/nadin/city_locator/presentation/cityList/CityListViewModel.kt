package com.nadin.city_locator.presentation.cityList

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadin.city_locator.domain.usecase.GetAllCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    getAllCitiesUseCase: GetAllCitiesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CityListState())
    val state = _state.asStateFlow()

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