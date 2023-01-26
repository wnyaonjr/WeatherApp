package com.gcash.weatherapp.features.weather.current.framework.usecases

import android.content.Context
import com.gcash.weatherapp.R
import com.gcash.weatherapp.features.location.framework.usecases.OpenLocationSourceSettingsUseCase
import com.gcash.weatherapp.features.weather.shared.framework.DisplayConfirmationDialogUseCase
import javax.inject.Inject

class DisplayLocationServicesDialogUseCase @Inject constructor(
    private val displayConfirmationDialogUseCase: DisplayConfirmationDialogUseCase,
    private val openLocationSourceSettingsUseCase: OpenLocationSourceSettingsUseCase,
) {
    operator fun invoke(context: Context) {
        displayConfirmationDialogUseCase(
            context = context,
            title = R.string.enable_share_location_title,
            message = R.string.enable_share_location_msg,
            confirmLabel = R.string.settings,
            onConfirm = {
                openLocationSourceSettingsUseCase()
            }
        )
    }
}