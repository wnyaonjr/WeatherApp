package com.gcash.weatherapp.core.framework

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OpenApplicationSettingsUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() {
        context.startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", context.packageName, null)
            flags = FLAG_ACTIVITY_NEW_TASK
        })
    }
}