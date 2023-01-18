package com.gcash.weatherapp.features.weather.shared.service

import com.gcash.weatherapp.features.weather.current.network.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
    ): CurrentWeatherResponse
}