package com.gcash.weatherapp.features.weather.main.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("show_if_true")
fun bindViewShowIfTrue(view: View, flag: Boolean?) {
    view.visibility = if (flag == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}


@BindingAdapter("show_if_false")
fun bindViewShowIfFalse(view: View, flag: Boolean?) {
    view.visibility = if (flag == false) {
        View.VISIBLE
    } else {
        View.GONE
    }
}