package com.gcash.weatherapp.features.weather.current.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WindResponse(
    @Json(name = "speed")
    val speed: Double,
    @Json(name = "deg")
    val deg: Int
)