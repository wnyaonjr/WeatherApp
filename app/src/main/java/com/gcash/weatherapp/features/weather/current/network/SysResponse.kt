package com.gcash.weatherapp.features.weather.current.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SysResponse(
    @Json(name = "type")
    val type: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "country")
    val country: String,
    @Json(name = "sunrise")
    val sunrise: Long,
    @Json(name = "sunset")
    val sunset: Long
)