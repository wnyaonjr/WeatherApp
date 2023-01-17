package com.gcash.weatherapp.features.datetime.framework.usecases

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class ConvertToDateTimeFormatUseCase @Inject constructor() {
    operator fun invoke(
        input: Long,
        pattern: String
    ): String = DateTimeFormat.forPattern(pattern).print(DateTime(input))

    companion object {
        const val TIME_FORMAT_AM_PM = "hh:mm aa"
    }
}