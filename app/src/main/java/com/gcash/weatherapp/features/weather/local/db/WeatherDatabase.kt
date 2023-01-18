package com.gcash.weatherapp.features.weather.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gcash.weatherapp.features.weather.local.dao.WeatherDao
import com.gcash.weatherapp.features.weather.local.db.entity.WeatherEntity

/**
 * Database definition which contains movie entity and dao
 */
@Database(
    entities = [
        WeatherEntity::class
    ],
    version = 1,
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}