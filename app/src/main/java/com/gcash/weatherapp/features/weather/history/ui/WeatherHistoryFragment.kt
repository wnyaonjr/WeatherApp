package com.gcash.weatherapp.features.weather.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.gcash.weatherapp.R
import com.gcash.weatherapp.databinding.FragmentWeatherHistoryBinding
import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertToDateTimeFormatUseCase
import com.gcash.weatherapp.features.weather.shared.domain.Weather
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Contains the layout for the weather list
 */
@AndroidEntryPoint
class WeatherHistoryFragment : Fragment() {
    private val viewModel: WeatherHistoryViewModel by viewModels()

    @Inject
    lateinit var convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeatherHistoryBinding.inflate(inflater, container, false).apply {
        composeView.setContent {
            MaterialTheme {
                WeatherListLayout(viewModel, convertToDateTimeFormatUseCase)
            }
        }
    }.root
}

@Composable
fun WeatherListLayout(
    viewModel: WeatherHistoryViewModel,
    convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase
) {
    val weatherHistory by viewModel.weatherHistory.observeAsState()
    WeatherListLayout(weatherHistory, convertToDateTimeFormatUseCase)
}

@Composable
fun WeatherListLayout(
    weatherHistory: List<Weather>?,
    convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase
) {
    if (!weatherHistory.isNullOrEmpty()) {
        LazyColumn {
            items(weatherHistory) { weather ->
                WeatherItemLayout(weather, convertToDateTimeFormatUseCase)
            }
        }
    } else {
        //TODO display empty screen
    }
}

@Composable
fun WeatherItemLayout(
    weather: Weather,
    convertToDateTimeFormatUseCase: ConvertToDateTimeFormatUseCase,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(
                    R.string.last_update,
                    convertToDateTimeFormatUseCase(
                        weather.timestamp,
                        ConvertToDateTimeFormatUseCase.TIMESTAMP_FORMAT
                    ).orEmpty()
                ),
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = stringResource(
                    R.string.weather_location,
                    weather.city,
                    weather.country
                ),
                style = MaterialTheme.typography.h6,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    modifier = Modifier.size(48.dp),
                    model = weather.iconUrl,
                    contentDescription = null,
                    error = painterResource(R.drawable.ic_broken_image),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f, fill = true)) {
                    Text(
                        text = stringResource(R.string.weather_temperature, weather.temperature),
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Text(
                        text = stringResource(
                            R.string.weather_info,
                            weather.weatherMain.orEmpty(),
                            weather.weatherDescription.orEmpty()
                        ),
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

