package com.gcash.weatherapp.features.weather.current.ui.screen

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gcash.weatherapp.R
import com.gcash.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.gcash.weatherapp.features.weather.current.framework.usecases.CurrentWeatherUseCases
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
    lateinit var currentWeatherUseCases: CurrentWeatherUseCases

    private val locationRequestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                handleLocationPermissionGranted()
            } else {
                currentWeatherUseCases.displayLocationPermissionDialogUseCase(
                    context = requireContext()
                )
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
        initObservers()
        checkLocationPermission()
    }

    private fun initObservers() {
        viewModel.refresh.observe(viewLifecycleOwner, ::handleRefresh)
        viewModel.resultError.observe(viewLifecycleOwner, ::handleResultError)
    }

    private fun handleResultError(unit: Unit?) {
        currentWeatherUseCases.showSnackBarUseCase(
            view = binding.root,
            message = R.string.request_error
        )
    }

    private fun handleRefresh(unit: Unit?) {
        checkLocationPermission()
    }

    private fun initViews() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun checkLocationPermission() {
        if (currentWeatherUseCases.getLocationPermissionUseCase(requireContext())) {
            handleLocationPermissionGranted()
        } else {
            locationRequestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun handleLocationPermissionGranted() {
        if (currentWeatherUseCases.checkLocationAccessUseCase(requireContext())) {
            getCurrentLocation()
        } else {
            currentWeatherUseCases.displayLocationServicesDialogUseCase(
                context = requireContext(),
            )
        }
    }

    private fun getCurrentLocation() {
        currentWeatherUseCases.getCurrentLocationUseCase(
            fusedLocationClient = fusedLocationClient,
            locationListener = viewModel::onLocationUpdate
        )
    }
}
