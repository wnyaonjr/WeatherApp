package com.gcash.weatherapp.features.weather.current.framework.usecases

import com.gcash.weatherapp.features.location.framework.usecases.CheckLocationAccessUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetCurrentLocationUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetLocationPermissionUseCase
import javax.inject.Inject

class CurrentWeatherUseCases @Inject constructor(
    val checkLocationAccessUseCase: CheckLocationAccessUseCase,
    val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    val getLocationPermissionUseCase: GetLocationPermissionUseCase,
)