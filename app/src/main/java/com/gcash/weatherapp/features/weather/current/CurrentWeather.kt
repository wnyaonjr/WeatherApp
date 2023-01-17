package com.gcash.weatherapp.features.weather.current

data class CurrentWeather(
    val temperature: Double,
    val sunrise: Long,
    val sunset: Long,
    val country: String,
    val city: String
)