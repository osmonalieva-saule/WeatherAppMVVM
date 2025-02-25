package com.example.weatherapp.data

import com.example.weatherapp.data.model.WeatherResponse

class WeatherRepozitory {
    private  val apiKey = "1334b8a605334a33b6f132803251202"

    suspend fun getCurrentWeather(location: String): WeatherResponse {
        return RetrofitClient.retrofitService.getCurrentWeather(apiKey, location)
    }
}