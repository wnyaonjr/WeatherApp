package com.gcash.weatherapp.features.location.framework.usecases

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OpenLocationSourceSettingsUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() {
        context.startActivity(Intent().apply {
            action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
            flags = FLAG_ACTIVITY_NEW_TASK
        })
    }
}