package com.gcash.weatherapp.features.location.framework.usecases

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class GetLocationPermissionUseCase @Inject constructor() {
    operator fun invoke(context: Context) =
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
}