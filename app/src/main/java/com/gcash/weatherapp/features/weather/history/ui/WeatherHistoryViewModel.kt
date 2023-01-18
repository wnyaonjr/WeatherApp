package com.gcash.weatherapp.features.weather.history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gcash.weatherapp.features.weather.history.framework.usecases.GetWeatherHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherHistoryViewModel @Inject constructor(
    getWeatherHistoryUseCase: GetWeatherHistoryUseCase,
) : ViewModel() {
    val weatherHistory = getWeatherHistoryUseCase().asLiveData()
}