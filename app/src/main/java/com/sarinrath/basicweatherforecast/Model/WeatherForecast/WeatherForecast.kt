package com.sarinrath.basicweatherforecast.Model.WeatherForecast

data class WeatherForecast(val city: City,
                           val cnt: Int = 0,
                           val cod: String = "",
                           val message: Int = 0,
                           val list: List<ListItem>?)