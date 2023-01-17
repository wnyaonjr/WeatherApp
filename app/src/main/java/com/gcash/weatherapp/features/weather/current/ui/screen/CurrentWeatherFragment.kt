package com.gcash.weatherapp.features.weather.current.ui.screen

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gcash.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.gcash.weatherapp.features.location.framework.usecases.CheckLocationAccessUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetCurrentLocationUseCase
import com.gcash.weatherapp.features.location.framework.usecases.GetLocationPermissionUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var checkLocationAccessUseCase: CheckLocationAccessUseCase

    @Inject
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    @Inject
    lateinit var getLocationPermissionUseCase: GetLocationPermissionUseCase

    private val locationRequestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                handleLocationPermissionGranted()
            } else {
                //TODO display location access error
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCurrentWeatherBinding.inflate(
        inflater, container, false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        checkLocationPermission()
    }

    private fun initViews() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun checkLocationPermission() {
        if (getLocationPermissionUseCase(requireContext())) {
            handleLocationPermissionGranted()
        } else {
            locationRequestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun handleLocationPermissionGranted() {
        if (checkLocationAccessUseCase(requireContext())) {
            getCurrentLocation()
        } else {
            //TODO location handling error
        }
    }

    private fun getCurrentLocation() {
        getCurrentLocationUseCase(
            fusedLocationClient = fusedLocationClient,
            locationListener = viewModel::onLocationUpdate
        )
    }
}
