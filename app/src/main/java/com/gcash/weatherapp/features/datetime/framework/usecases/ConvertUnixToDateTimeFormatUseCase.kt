package com.gcash.weatherapp.features.datetime.framework.usecases

import org.joda.time.DateTimeConstants
import javax.inject.Inject

class ConvertUnixToDateTimeFormatUseCase @Inject constructor(
    private val convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase
) {
    operator fun invoke(
        input: Long?,
        pattern: String
    ): String? = if (input != null)
        convertToDateTimeFormatUseCase(input * DateTimeConstants.MILLIS_PER_SECOND, pattern)
    else null
}