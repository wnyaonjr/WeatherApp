package com.gcash.weatherapp

import androidx.multidex.MultiDexApplication
import com.gcash.weatherapp.core.framework.InitLoggingUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Application definition, contains starting of logger when build type is debug
 */
@HiltAndroidApp
class WeatherApp : MultiDexApplication() {

    @Inject
    lateinit var initLoggingUseCase: InitLoggingUseCase

    override fun onCreate() {
        super.onCreate()
        initLoggingUseCase()
    }
}