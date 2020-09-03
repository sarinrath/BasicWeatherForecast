package com.sarinrath.basicweatherforecast.Model.WeatherData

data class Main(val temp: Double = 0.0,
                val temp_min: Double = 0.0,
                val humidity: Int = 0,
                val pressure: Int = 0,
                val temp_max: Double = 0.0)