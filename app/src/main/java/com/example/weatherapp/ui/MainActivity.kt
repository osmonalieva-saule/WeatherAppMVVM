package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.data.model.WeatherResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.weather.observe(this, Observer { weather ->
            showWeather(weather)
        })

        viewModel.error.observe(this, Observer { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        })

        viewModel.loadWeather("Bishkek")
    }

    private fun showWeather(weather: WeatherResponse) {
        Log.d("Weather", "Full Response: $weather")
        binding.apply {
            tvTemp.text = weather.current?.tempC.toString()
            tvDesc.text = weather.current?.isDay.toString()
            tvWind.text = weather.current?.windDegree.toString()
            tvSunset.text = weather.current?.lastUpdated.toString()
            tvFeelLike.text = weather.current?.feelslikeC.toString()
            imgWeather.load("https:${weather.current?.condition?.icon}")

            val iconUrl = "https:${weather.current?.condition?.icon}"
            Glide.with(this@MainActivity)
                .load(iconUrl)
                .into(imgWeather)
        }

    }
}
