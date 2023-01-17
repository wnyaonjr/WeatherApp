package com.gcash.weatherapp.features.weather.repository

import com.gcash.weatherapp.features.weather.current.network.CurrentWeatherResponse
import com.gcash.weatherapp.features.weather.service.WeatherService

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
    ): CurrentWeatherResponse
}

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String
    ) = weatherService.getCurrentWeather(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey,
        units = units
    )

}