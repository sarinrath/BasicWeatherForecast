package com.sarinrath.basicweatherforecast.Model.WeatherForecast

data class ListItem(val dt: Int = 0,
                    val pop: Double = 0.0,
                    val visibility: Int = 0,
                    val dt_txt: String = "",
                    val weather: List<WeatherItem>?,
                    val main: Main,
                    val clouds: Clouds,
                    val sys: Sys,
                    val wind: Wind)