package com.gcash.weatherapp.features.weather.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.gcash.weatherapp.R
import com.gcash.weatherapp.databinding.FragmentWeatherHistoryBinding
import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertToDateTimeFormatUseCase
import com.gcash.weatherapp.features.datetime.framework.usecases.ConvertUnixToDateTimeFormatUseCase
import com.gcash.weatherapp.features.weather.history.framework.usecases.WeatherHistoryUseCases
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
    lateinit var weatherHistoryUseCases: WeatherHistoryUseCases

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeatherHistoryBinding.inflate(inflater, container, false).apply {
        composeView.setContent {
            MaterialTheme {
                WeatherListLayout(viewModel, weatherHistoryUseCases)
            }
        }
    }.root
}

@Composable
fun WeatherListLayout(
    viewModel: WeatherHistoryViewModel,
    weatherHistoryUseCases: WeatherHistoryUseCases
) {
    val weatherHistory by viewModel.weatherHistory.observeAsState()
    WeatherListLayout(weatherHistory, weatherHistoryUseCases)
}

@Composable
fun WeatherListLayout(
    weatherHistory: List<Weather>?,
    weatherHistoryUseCases: WeatherHistoryUseCases
) {
    if (!weatherHistory.isNullOrEmpty()) {
        LazyColumn {
            items(weatherHistory) { weather ->
                WeatherItemLayout(weather, weatherHistoryUseCases)
            }
        }
    } else {
        NoWeatherHistoryDisplay()
    }
}

@Composable
fun WeatherItemLayout(
    weather: Weather,
    weatherHistoryUseCases: WeatherHistoryUseCases,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            WeatherHeader(weather, weatherHistoryUseCases)
            WeatherInformationLayout(weather)
            WeatherSunriseSunsetLayout(weather, weatherHistoryUseCases)
        }
    }
}

@Composable
fun WeatherHeader(
    weather: Weather,
    weatherHistoryUseCases: WeatherHistoryUseCases
) {
    Column {
        Text(
            text = stringResource(
                R.string.timestamp,
                weatherHistoryUseCases.convertToDateTimeFormatUseCase(
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
    }
}

@Composable
fun WeatherSunriseSunsetLayout(
    weather: Weather,
    weatherHistoryUseCases: WeatherHistoryUseCases
) {
    Row {
        Text(
            text = stringResource(
                R.string.sunrise,
                weatherHistoryUseCases.convertUnixToDateTimeFormatUseCase(
                    weather.sunrise,
                    ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
                ).orEmpty()
            ),
            style = MaterialTheme.typography.caption,
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(
                R.string.sunset,
                weatherHistoryUseCases.convertUnixToDateTimeFormatUseCase(
                    weather.sunset,
                    ConvertToDateTimeFormatUseCase.TIME_FORMAT_AM_PM
                ).orEmpty()
            ),
            style = MaterialTheme.typography.caption,
        )
    }
}

@Composable
fun WeatherInformationLayout(weather: Weather) {
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

/**
 * layout when no weather history found
 */
@Composable
fun NoWeatherHistoryDisplay() {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_no_weather),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.no_weather_history),
            style = MaterialTheme.typography.subtitle1,
            color = colorResource(R.color.black),
        )
    }
}

/**
 * Test function for current weather as an item list
 */
@Preview
@Composable
fun WeatherItemLayoutPreview() {
    MaterialTheme {
        WeatherItemLayout(
            Weather(
                temperature = 9.74,
                sunrise = 1674746173,
                sunset = 1674782714,
                country = "US",
                city = "Mountain View",
                weatherMain = "Clear",
                weatherDescription = "clear sky",
                weatherIcon = "01n",
                timestamp = 1674731310324L
            ),
            WeatherHistoryUseCases(
                convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase(),
                convertUnixToDateTimeFormatUseCase = ConvertUnixToDateTimeFormatUseCase(
                    convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase()
                )
            )
        )
    }
}

/**
 * Test function for current weather timestamp and location
 */
@Preview
@Composable
fun WeatherHeaderPreview() {
    MaterialTheme {
        WeatherHeader(
            Weather(
                temperature = 9.74,
                sunrise = 1674746173,
                sunset = 1674782714,
                country = "US",
                city = "Mountain View",
                weatherMain = "Clear",
                weatherDescription = "clear sky",
                weatherIcon = "01n",
                timestamp = 1674731310324L
            ),
            WeatherHistoryUseCases(
                convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase(),
                convertUnixToDateTimeFormatUseCase = ConvertUnixToDateTimeFormatUseCase(
                    convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase()
                )
            )
        )
    }
}

/**
 * Test function for current weather temperature, and weather description
 */
@Preview
@Composable
fun WeatherInformationLayoutPreview() {
    MaterialTheme {
        WeatherInformationLayout(
            Weather(
                temperature = 9.74,
                sunrise = 1674746173,
                sunset = 1674782714,
                country = "US",
                city = "Mountain View",
                weatherMain = "Clear",
                weatherDescription = "clear sky",
                weatherIcon = "01n",
                timestamp = 1674731310324L
            )
        )
    }
}

/**
 * Test function for sunrise and sunset of current weather
 */
@Preview
@Composable
fun WeatherSunriseSunsetLayoutPreview() {
    MaterialTheme {
        WeatherSunriseSunsetLayout(
            Weather(
                temperature = 9.74,
                sunrise = 1674746173,
                sunset = 1674782714,
                country = "US",
                city = "Mountain View",
                weatherMain = "Clear",
                weatherDescription = "clear sky",
                weatherIcon = "01n",
                timestamp = 1674731310324L
            ),
            WeatherHistoryUseCases(
                convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase(),
                convertUnixToDateTimeFormatUseCase = ConvertUnixToDateTimeFormatUseCase(
                    convertToDateTimeFormatUseCase = ConvertToDateTimeFormatUseCase()
                )
            )
        )
    }
}



/**
 * Test function when no weather history display
 */
@Preview
@Composable
fun NoWeatherHistoryDisplayPreview() {
    MaterialTheme {
        NoWeatherHistoryDisplay()
    }
}

