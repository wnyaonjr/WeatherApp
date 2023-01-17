package com.gcash.weatherapp.core.framework

import com.gcash.weatherapp.BuildConfig
import timber.log.Timber
import javax.inject.Inject

/**
 * initialize timber logging during debug build type
 */
class InitLoggingUseCase @Inject constructor() {
    operator fun invoke() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}