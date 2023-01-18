package com.gcash.weatherapp.features.weather.history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gcash.weatherapp.features.weather.history.framework.usecases.WeatherHistoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherHistoryViewModel @Inject constructor(
    private val weatherHistoryUseCases: WeatherHistoryUseCases
) : ViewModel() {

    val weatherHistory = weatherHistoryUseCases.getWeatherHistoryUseCase().asLiveData()
}