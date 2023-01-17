package com.gcash.weatherapp.features.weather.current.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloudsResponse(
    @Json(name = "all")
    val all: Int
)