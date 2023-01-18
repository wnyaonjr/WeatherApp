package com.gcash.weatherapp.features.weather.history.framework.usecases

import javax.inject.Inject

class WeatherHistoryUseCases @Inject constructor(
    val getWeatherHistoryUseCase: GetWeatherHistoryUseCase,
    val getWeatherCountUseCase: GetWeatherCountUseCase,
)