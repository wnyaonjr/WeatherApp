package com.gcash.weatherapp.features.datetime.framework.usecases

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class ConvertToDateTimeFormatUseCase @Inject constructor() {
    operator fun invoke(
        input: Long?,
        pattern: String
    ): String? = if (input != null)
        DateTimeFormat.forPattern(pattern).print(DateTime(input))
    else null

    companion object {
        const val TIME_FORMAT_AM_PM = "hh:mm aa"
        const val TIMESTAMP_FORMAT = "hh:mm:ss aa"
    }
}