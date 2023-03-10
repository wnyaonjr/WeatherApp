package com.gcash.weatherapp.features.weather.current.ui.screen

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gcash.weatherapp.R
import com.gcash.weatherapp.features.weather.shared.domain.Weather
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("weather_location")
fun bindWeatherLocation(textView: MaterialTextView, weather: Weather?) {
    textView.apply {
        if (weather != null) {
            text = resources.getString(R.string.weather_location, weather.city, weather.country)
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}

@BindingAdapter("weather_temperature")
fun bindWeatherTemperature(textView: MaterialTextView, temperature: Double?) {
    textView.apply {
        if (temperature != null) {
            text = resources.getString(R.string.weather_temperature, temperature)
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}

@BindingAdapter("weather_icon")
fun bindWeatherIcon(imageView: AppCompatImageView, iconUrl: String?) {
    Glide.with(imageView)
        .load(iconUrl)
        .error(R.drawable.ic_broken_image)
        .into(imageView)
}

@BindingAdapter("weather_info")
fun bindWeatherIcon(textView: MaterialTextView, weather: Weather?) {
    textView.apply {
        if (weather?.weatherMain != null && weather.weatherDescription != null) {
            text = resources.getString(
                R.string.weather_info,
                weather.weatherMain,
                weather.weatherDescription
            )
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}

@BindingAdapter("weather_sunrise")
fun bindWeatherSunrise(textView: MaterialTextView, sunrise: String?) {
    textView.apply {
        if (sunrise != null) {
            text = resources.getString(
                R.string.sunrise,
                sunrise
            )
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}

@BindingAdapter("weather_sunset")
fun bindWeatherSunset(textView: MaterialTextView, sunset: String?) {
    textView.apply {
        if (sunset != null) {
            text = resources.getString(
                R.string.sunset,
                sunset
            )
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}

@BindingAdapter("weather_last_update")
fun bindWeatherLastUpdate(textView: MaterialTextView, timestamp: String?) {
    textView.apply {
        if (timestamp != null) {
            text = resources.getString(
                R.string.timestamp,
                timestamp
            )
        } else {
            setText(R.string.indeterminate_placeholder)
        }
    }
}