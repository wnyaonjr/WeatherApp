package com.gcash.weatherapp.features.weather.shared.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gcash.weatherapp.features.weather.shared.domain.Weather

/**
 * Class definition of weather for saving in local database
 */
@Entity(tableName = "table_weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temperature: Double,
    val sunrise: Long,
    val sunset: Long,
    val country: String,
    val city: String,
    val weatherMain: String?,
    val weatherDescription: String?,
    val weatherIcon: String?,
)

fun WeatherEntity.toDomainModel() = Weather(
    temperature = temperature,
    sunrise = sunrise,
    sunset = sunset,
    country = country,
    city = city,
    weatherMain = weatherMain,
    weatherDescription = weatherDescription,
    weatherIcon = weatherIcon
)

fun List<WeatherEntity>.toDomainModel() = map {
    it.toDomainModel()
}