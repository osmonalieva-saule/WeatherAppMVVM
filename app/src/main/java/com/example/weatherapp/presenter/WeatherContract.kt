package com.example.weatherapp.presenter

import com.example.weatherapp.model.WeatherResponse

interface WeatherContract {
    interface View {
        fun showWeather(weatherResponse: WeatherResponse)
        fun showError(message: String)

    }

    interface Presenter {
        fun loadData(location: String)
        fun onDestroy()
    }
}