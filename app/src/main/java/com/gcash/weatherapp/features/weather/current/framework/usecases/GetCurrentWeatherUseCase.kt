package com.gcash.weatherapp.features.weather.current.framework.usecases

import com.gcash.weatherapp.BuildConfig
import com.gcash.weatherapp.core.repository.handler.DefaultFlowRequestHandler
import com.gcash.weatherapp.core.repository.handler.FlowRequestHandler
import com.gcash.weatherapp.features.weather.current.network.toDomainModel
import com.gcash.weatherapp.features.weather.shared.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) :
    FlowRequestHandler by DefaultFlowRequestHandler {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        apiKey: String = BuildConfig.API_KEY,
        units: String = DEFAULT_UNITS,
    ) = safeApiCall {
        weatherRepository.getCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey,
            units = units
        ).toDomainModel().apply {
            weatherRepository.saveWeather(this)
        }
    }

    operator fun invoke() = weatherRepository.getLatestWeather()

    companion object {
        private const val DEFAULT_UNITS = "metric"
    }
}