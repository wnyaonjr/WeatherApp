package com.gcash.weatherapp.features.datetime.framework.usecases

import org.junit.Assert
import org.junit.Test

class ConvertToDateTimeFormatUseCaseTest {
    private val convertToDateTimeFormatUseCase by lazy {
        ConvertToDateTimeFormatUseCase()
    }

    @Test
    fun convert_sunrise_test() {
        val actual = convertToDateTimeFormatUseCase(
            input = 1673968853, pattern = ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
        )

        Assert.assertEquals("08:59 AM", actual)

        //sunrise=1673968853, sunset=1674004530
    }
}