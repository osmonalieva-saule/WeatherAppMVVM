package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.WeatherResponse

import com.example.weatherapp.presenter.WeatherContract
import com.example.weatherapp.presenter.WeatherPresenter

class MainActivity : AppCompatActivity(), WeatherContract.View {

    private lateinit var binding:ActivityMainBinding

    private val presenter by lazy {
        WeatherPresenter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.loadData("Bishkek")
    }
    override fun showWeather(weatherResponse: WeatherResponse) {
        Log.d("Weather", "Full Response: $weatherResponse")
        Log.d("Weather", "Weather data received: $weatherResponse")
        binding.apply {
            tvTemp.text = weatherResponse.current?.tempC.toString()
            tvDesc.text = weatherResponse.current?.isDay.toString()
            tvWind.text = weatherResponse.current?.windDegree.toString()
            tvSunset.text = weatherResponse.current?.lastUpdated.toString()
            tvFeelLike.text = weatherResponse.current?.feelslikeC.toString()
            imgWeather.load("https:${weatherResponse.current?.condition?.icon}")

            val iconUrl = "https:${weatherResponse.current?.condition?.icon}"
            Glide.with(this@MainActivity)
                .load(iconUrl)
                .into(imgWeather)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}
