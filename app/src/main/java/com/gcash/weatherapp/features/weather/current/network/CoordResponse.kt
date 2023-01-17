package com.gcash.weatherapp.features.weather.current.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordResponse(
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "lat")
    val lat: Double
)