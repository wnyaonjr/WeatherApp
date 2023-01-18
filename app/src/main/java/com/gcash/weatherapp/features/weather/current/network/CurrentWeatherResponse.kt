package com.gcash.weatherapp.features.weather.current.network


import com.gcash.weatherapp.features.weather.shared.domain.Weather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "coord")
    val coord: CoordResponse,
    @Json(name = "weather")
    val weather: List<WeatherResponse>,
    @Json(name = "base")
    val base: String,
    @Json(name = "main")
    val main: MainResponse,
    @Json(name = "visibility")
    val visibility: Int,
    @Json(name = "wind")
    val wind: WindResponse,
    @Json(name = "clouds")
    val clouds: CloudsResponse,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "sys")
    val sys: SysResponse,
    @Json(name = "timezone")
    val timezone: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "cod")
    val cod: Int
)

fun CurrentWeatherResponse.toDomainModel() = Weather(
    temperature = main.temp,
    sunrise = sys.sunrise,
    sunset = sys.sunset,
    country = sys.country,
    city = name,
    weatherMain = weather.firstOrNull()?.main,
    weatherDescription = weather.firstOrNull()?.description,
    weatherIcon = weather.firstOrNull()?.icon,
)