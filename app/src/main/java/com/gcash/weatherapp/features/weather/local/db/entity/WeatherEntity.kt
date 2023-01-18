package com.gcash.weatherapp.features.weather.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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