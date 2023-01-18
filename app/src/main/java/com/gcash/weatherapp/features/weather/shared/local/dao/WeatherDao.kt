package com.gcash.weatherapp.features.weather.shared.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gcash.weatherapp.features.weather.shared.local.db.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * Definition of supported functions for local weather data
 */
@Dao
interface WeatherDao {
    @Query("SELECT * FROM table_weather ORDER BY id DESC")
    fun getAll(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg entity: WeatherEntity)

    @Query("DELETE from table_weather")
    fun delete()

    @Query("SELECT * FROM table_weather ORDER BY id DESC LIMIT 1")
    fun getLatest(): Flow<WeatherEntity?>
}