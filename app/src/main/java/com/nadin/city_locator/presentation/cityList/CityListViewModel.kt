package com.nadin.city_locator.presentation.cityList

import androidx.lifecycle.ViewModel
import com.nadin.city_locator.domain.usecase.GetAllCitiesUseCase
import com.nadin.city_locator.domain.usecase.SortCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    getAllCitiesUseCase: GetAllCitiesUseCase,
    sortCitiesUseCase: SortCitiesUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(CityListState())
    val state = _state.asStateFlow()

    init {
        val cityItems = getAllCitiesUseCase()
        _state.update {
            it.copy(
                cities = sortCitiesUseCase(cityItems)
            )
        }
    }
}