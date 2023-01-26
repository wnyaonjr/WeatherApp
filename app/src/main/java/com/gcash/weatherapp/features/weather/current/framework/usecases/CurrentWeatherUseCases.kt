package com.gcash.weatherapp.features.weather.current.framework.usecases

import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertToDateTimeFormatUseCase
import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertUnixToDateTimeFormatUseCase
import com.gcash.weatherapp.features.location.framework.usecases.CheckLocationAccessUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetCurrentLocationUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetLocationPermissionUseCase
import com.gcash.weatherapp.features.weather.shared.framework.ShowSnackBarUseCase
import javax.inject.Inject

class CurrentWeatherUseCases @Inject constructor(
    val checkLocationAccessUseCase: CheckLocationAccessUseCase,
    val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    val getLocationPermissionUseCase: GetLocationPermissionUseCase,
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase,
    val convertUnixToDateTimeFormatUseCase: ConvertUnixToDateTimeFormatUseCase,
    val showSnackBarUseCase: ShowSnackBarUseCase,
    val displayLocationPermissionDialogUseCase: DisplayLocationPermissionDialogUseCase,
    val displayLocationServicesDialogUseCase: DisplayLocationServicesDialogUseCase,
)