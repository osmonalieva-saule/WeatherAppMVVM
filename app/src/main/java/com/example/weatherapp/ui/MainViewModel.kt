package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepozitory
import com.example.weatherapp.data.model.WeatherResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = WeatherRepozitory()

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getCurrentWeather(city)
                _weather.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Ошибка загрузки данных")
            }
        }
    }
}