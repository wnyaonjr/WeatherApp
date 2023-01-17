package com.gcash.weatherapp.features.location.framework.usecases

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationCancellationSource: CancellationTokenSource
) {
    @SuppressLint("MissingPermission")
    operator fun invoke(
        fusedLocationClient: FusedLocationProviderClient,
        locationListener: (location: Location?) -> Unit
    ) {
        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY,
            locationCancellationSource.token
        ).addOnSuccessListener { location: Location? ->
            locationListener.invoke(location)
        }
    }
}