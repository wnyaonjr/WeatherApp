package com.gcash.weatherapp.features.weather.shared.repository

import com.gcash.weatherapp.features.weather.current.network.CurrentWeatherResponse
import com.gcash.weatherapp.features.weather.shared.domain.Weather
import com.gcash.weatherapp.features.weather.shared.domain.toEntityModel
import com.gcash.weatherapp.features.weather.shared.local.dao.WeatherDao
import com.gcash.weatherapp.features.weather.shared.local.db.WeatherDatabase
import com.gcash.weatherapp.features.weather.shared.local.db.entity.toDomainModel
import com.gcash.weatherapp.features.weather.shared.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
    ): CurrentWeatherResponse

    suspend fun saveWeather(weather: Weather)

    fun getLatestWeather(): Flow<Weather?>
    fun getWeatherHistory(): Flow<List<Weather>>
}

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    private val weatherDao: WeatherDao by lazy {
        weatherDatabase.weatherDao()
    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String
    ) = weatherService.getCurrentWeather(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey,
        units = units
    )

    override suspend fun saveWeather(weather: Weather) {
        withContext(Dispatchers.IO) {
            weatherDao.insertAll(weather.toEntityModel())
        }
    }

    override fun getLatestWeather() = weatherDao.getLatest().map { it?.toDomainModel() }

    override fun getWeatherHistory() = weatherDao.getAll().map {
        it.toDomainModel()
    }
}