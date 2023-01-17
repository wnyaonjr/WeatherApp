package com.gcash.weatherapp.core.di

import com.gcash.weatherapp.features.weather.repository.WeatherRepository
import com.gcash.weatherapp.features.weather.repository.WeatherRepositoryImpl
import com.gcash.weatherapp.features.weather.service.WeatherService
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
        weatherService: WeatherService
    ): WeatherRepository =
        WeatherRepositoryImpl(
            weatherService
        )
}