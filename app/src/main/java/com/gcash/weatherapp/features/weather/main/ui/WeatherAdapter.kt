package com.gcash.weatherapp.features.weather.main.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gcash.weatherapp.features.weather.current.ui.screen.CurrentWeatherFragment
import com.gcash.weatherapp.features.weather.history.ui.WeatherHistoryFragment

class WeatherAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = TAB_COUNT

    override fun createFragment(position: Int) = when (position) {
        TAB_POSITION_CURRENT -> CurrentWeatherFragment()
        else -> WeatherHistoryFragment()
    }

    companion object {
        const val TAB_COUNT = 2
        const val TAB_POSITION_CURRENT = 0
        const val TAB_POSITION_HISTORY = 1
    }
}