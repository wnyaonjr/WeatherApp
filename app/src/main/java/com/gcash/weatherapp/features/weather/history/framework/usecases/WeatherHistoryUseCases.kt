package com.gcash.weatherapp.features.weather.history.framework.usecases

import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertToDateTimeFormatUseCase
import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertUnixToDateTimeFormatUseCase
import javax.inject.Inject

class WeatherHistoryUseCases @Inject constructor(
    val convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase,
    val convertUnixToDateTimeFormatUseCase: ConvertUnixToDateTimeFormatUseCase,
)