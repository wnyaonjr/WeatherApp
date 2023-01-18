package com.gcash.weatherapp.features.weather.shared.domain

import com.gcash.weatherapp.features.weather.shared.local.db.entity.WeatherEntity

data class Weather(
    val temperature: Double,
    val sunrise: Long,
    val sunset: Long,
    val country: String,
    val city: String,
    val weatherMain: String?,
    val weatherDescription: String?,
    val weatherIcon: String?,
) {
    val iconUrl: String?
        get() = if (weatherIcon != null) {
            "https://openweathermap.org/img/wn/$weatherIcon@2x.png"
        } else null
}

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