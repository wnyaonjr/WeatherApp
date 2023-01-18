package com.gcash.weatherapp.features.datetime.framework.usecases

import org.junit.Assert
import org.junit.Test

class ConvertToDateTimeFormatUseCaseTest {
    private val convertToDateTimeFormatUseCase by lazy {
        ConvertToDateTimeFormatUseCase()
    }

    @Test
    fun current_time_test() {

        val currentTime = 1674052362759L
        val expected = "10:32 PM"


        val actual = convertToDateTimeFormatUseCase(
            input = currentTime, pattern = ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun sunrise_test() {

        val expected = "06:24 AM"


        val actual = convertToDateTimeFormatUseCase(
            input = 1673994284 * 1000L, pattern = ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun sunset_test() {

        val expected = "05:47 PM"

        val actual = convertToDateTimeFormatUseCase(
            input = 1674035221 * 1000L, pattern = ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
        )

        Assert.assertEquals(expected, actual)
    }
}