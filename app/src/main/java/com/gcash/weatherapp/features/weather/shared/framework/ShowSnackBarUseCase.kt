package com.gcash.weatherapp.features.weather.shared.framework

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ShowSnackBarUseCase @Inject constructor() {
    operator fun invoke(
        view: View,
        @StringRes message: Int,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(
            view,
            message,
            length
        ).show()
    }
}