package com.gcash.weatherapp.features.weather.current.ui.screen

import android.location.Location
import androidx.lifecycle.*
import com.gcash.weatherapp.core.network.ResultWrapper
import com.gcash.weatherapp.core.utils.SingleLiveEvent
import com.gcash.weatherapp.features.weather.current.framework.usecases.GetCurrentWeatherUseCase
import com.gcash.weatherapp.features.weather.shared.domain.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    val weather: LiveData<Weather?> = getCurrentWeatherUseCase().asLiveData()

    private val _refresh = SingleLiveEvent<Unit>()
    val refresh: LiveData<Unit> = _refresh


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLocationUpdate(location: Location?) {
        location?.let {
            viewModelScope.launch {
                getCurrentWeather(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            }
        }
    }

    private suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ) {
        getCurrentWeatherUseCase(
            latitude = latitude,
            longitude = longitude
        ).onEach { wrapper ->
            if (wrapper is ResultWrapper.Success) {
                handleSuccessGetCurrentWeather(wrapper.value)
            }
            handleResult(wrapper)
        }.launchIn(viewModelScope)
    }

    private fun handleSuccessGetCurrentWeather(weather: Weather) {
        Timber.d("$weather")
    }

    fun onRefresh() {
        _refresh.call()
    }

    private fun handleResult(resultWrapper: ResultWrapper<*>) {
        when (resultWrapper) {
            is ResultWrapper.Error -> handleError()
            is ResultWrapper.Loading -> handleLoading()
            is ResultWrapper.Success -> handleSuccess()
        }
    }

    private fun handleLoading() {
        _isLoading.value = true
    }

    private fun handleSuccess() {
        _isLoading.value = false
    }

    private fun handleError() {
        _isLoading.value = false

        //TODO display error message
    }

}