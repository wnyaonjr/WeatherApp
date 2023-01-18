package com.gcash.weatherapp.features.datetime.framework.usecases

import org.junit.Assert
import org.junit.Test

class ConvertToDateTimeFormatUseCaseTest {
    private val convertToDateTimeFormatUseCase by lazy {
        ConvertToDateTimeFormatUseCase()
    }

    @Test
    fun convert_sunrise_test() {

        val currentTime = 1674052362759L
        val expected = "10:32 PM"


        val actual = convertToDateTimeFormatUseCase(
            input = currentTime, pattern = ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
        )

        Assert.assertEquals(expected, actual)

        //sunrise=1673968853, sunset=1674004530
    }
}