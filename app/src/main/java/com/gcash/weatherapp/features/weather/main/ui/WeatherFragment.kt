package com.gcash.weatherapp.features.weather.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gcash.weatherapp.R
import com.gcash.weatherapp.databinding.FragmentWeatherBinding
import com.gcash.weatherapp.features.weather.main.ui.WeatherAdapter.Companion.TAB_POSITION_CURRENT
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeatherBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val viewPager = binding.viewPager
        viewPager.isUserInputEnabled = false
        viewPager.adapter = WeatherAdapter(this)

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int) = resources.getString(
        when (position) {
            TAB_POSITION_CURRENT -> R.string.current_weather
            else -> R.string.history
        }
    )
}