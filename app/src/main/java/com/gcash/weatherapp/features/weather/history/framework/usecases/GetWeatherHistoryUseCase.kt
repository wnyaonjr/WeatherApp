package com.gcash.weatherapp.features.weather.history.framework.usecases

import com.gcash.weatherapp.features.weather.shared.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherHistoryUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.getWeatherHistory()
}