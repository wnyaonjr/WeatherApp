package com.gcash.weatherapp.core.di

import android.content.Context
import androidx.room.Room
import com.gcash.weatherapp.features.weather.shared.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Definition of database dependency
 */
@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase =
        Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

    companion object {
        private const val DB_NAME = "weather_db"
    }
}