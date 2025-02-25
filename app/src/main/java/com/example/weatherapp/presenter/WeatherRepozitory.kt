package com.example.weatherapp.presenter

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.model.RetrofitClient

class WeatherRepozitory {
    private  val apiKey = "1334b8a605334a33b6f132803251202"

    suspend fun  getCurrentWeather(location: String): WeatherResponse {
        return RetrofitClient.retrofitService.getCurrentWeather(apiKey, location )
    }
}