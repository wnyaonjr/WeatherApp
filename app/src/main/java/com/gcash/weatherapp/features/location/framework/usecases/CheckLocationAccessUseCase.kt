package com.gcash.weatherapp.features.location.framework.usecases

import android.content.Context
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat
import javax.inject.Inject

class CheckLocationAccessUseCase @Inject constructor() {
    operator fun invoke(
        context: Context
    ): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }
}