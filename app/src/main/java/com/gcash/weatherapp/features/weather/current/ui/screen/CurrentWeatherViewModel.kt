package com.gcash.weatherapp.features.weather.current.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcash.weatherapp.core.network.ResultWrapper
import com.gcash.weatherapp.features.weather.current.CurrentWeather
import com.gcash.weatherapp.features.weather.current.framework.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    init {
        //TODO get current location
        getCurrentWeatherUseCase(
            latitude = 14.58863808696237,
            longitude = 121.02249354658818
        ).onEach { wrapper ->
            if (wrapper is ResultWrapper.Success) {
                handleSuccessGetCurrentWeather(wrapper.value)
            }
        }.launchIn(viewModelScope)
    }

    private fun handleSuccessGetCurrentWeather(currentWeather: CurrentWeather) {
        Timber.d("$currentWeather")
    }
}