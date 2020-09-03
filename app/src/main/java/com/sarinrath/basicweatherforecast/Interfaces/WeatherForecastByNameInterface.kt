package com.sarinrath.basicweatherforecast.Interfaces

import com.sarinrath.basicweatherforecast.Model.WeatherForecast.ListItem

/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */
interface WeatherForecastByNameInterface {

    fun getWeatherByName(nameCity:String, list: ArrayList<ListItem>?)

    fun fail(msg:String)
}