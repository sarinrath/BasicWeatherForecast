package com.sarinrath.basicweatherforecast.Interfaces


/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */

interface WeatherByNameInterface {
    fun getWeatherByName(nameCity:String, urlImage:String, status:String, description:String,
                         temperature:Double, tempMin:Double, tempMax:Double, humidity:Int)

    fun fail(msg:String)
}