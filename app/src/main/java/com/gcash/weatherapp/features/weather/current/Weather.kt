package com.gcash.weatherapp.features.weather.current

import com.gcash.weatherapp.features.weather.local.db.entity.WeatherEntity

data class Weather(
    val temperature: Double,
    val sunrise: Long,
    val sunset: Long,
    val country: String,
    val city: String,
    val weatherMain: String?,
    val weatherDescription: String?,
    val weatherIcon: String?,
)

fun Weather.toEntityModel() = WeatherEntity(
    temperature = temperature,
    sunrise = sunrise,
    sunset = sunset,
    country = country,
    city = city,
    weatherMain = weatherMain,
    weatherDescription = weatherDescription,
    weatherIcon = weatherIcon
)