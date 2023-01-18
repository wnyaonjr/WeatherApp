package com.gcash.weatherapp.core.di

import com.gcash.weatherapp.features.weather.shared.local.db.WeatherDatabase
import com.gcash.weatherapp.features.weather.shared.repository.WeatherRepository
import com.gcash.weatherapp.features.weather.shared.repository.WeatherRepositoryImpl
import com.gcash.weatherapp.features.weather.shared.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Definition of repository dependency for network requests
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        weatherService: WeatherService,
        weatherDatabase: WeatherDatabase
    ): WeatherRepository =
        WeatherRepositoryImpl(
            weatherService,
            weatherDatabase
        )
}