package com.sarinrath.basicweatherforecast.Model

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sarinrath.basicweatherforecast.Interfaces.HttpResponse
import com.sarinrath.basicweatherforecast.Interfaces.WeatherByNameInterface
import com.sarinrath.basicweatherforecast.Interfaces.WeatherForecastByNameInterface
import com.sarinrath.basicweatherforecast.Model.WeatherData.WeatherData
import com.sarinrath.basicweatherforecast.Model.WeatherForecast.ListItem
import com.sarinrath.basicweatherforecast.Model.WeatherForecast.WeatherForecast
import com.sarinrath.basicweatherforecast.Util.Network
import java.net.URLEncoder

/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */

class OpenWeatherData(var activity: AppCompatActivity){

    private val URL_BASE="http://api.openweathermap.org/"
    private val VERSION="2.5/"
    private val API_ID="&appid=8eacbfe2cda90e0c8aeb660de202c5bb"

    fun getWeatherByName(name:String, unit:String, WeatherByNameInterface: WeatherByNameInterface){
        val network= Network(activity)
        val name=URLEncoder.encode(name, "UTF-8")
        val section="data/"
        val method="weather?q=$name"
        var url="$URL_BASE$section$VERSION$method$API_ID$unit"
        network.httpRequest(activity.applicationContext, url, object: HttpResponse {
            override fun httpResponseSuccess(response: String) {
                var gson= Gson()
                var objectResonse=gson.fromJson(response, WeatherData::class.java)
                if(!objectResonse.name.isNullOrEmpty()) {
                    val nameCity = objectResonse.name!!
                    val urlImage = makeIconURL(objectResonse.weather?.get(0)?.icon!!)
                    val status = objectResonse.weather?.get(0)?.main!!
                    val description = objectResonse.weather?.get(0)?.description!!
                    val temperature = objectResonse.main?.temp!!
                    val tempMin = objectResonse.main?.temp_min!!
                    val tempMax = objectResonse.main?.temp_max!!
                    val humidity = objectResonse.main?.humidity!!
                    WeatherByNameInterface.getWeatherByName(nameCity, urlImage, status, description,
                        temperature, tempMin, tempMax, humidity)
                }else{
                    WeatherByNameInterface.fail("Could not get Weather data")
                }
            }
        })
    }

    fun getWeatherForecastByName(name:String, unit:String, WeatherByNameInterface : WeatherForecastByNameInterface){
        val network= Network(activity)
        val name=URLEncoder.encode(name, "UTF-8")
        val section="data/"
        val method="forecast?q=$name"
        var url="$URL_BASE$section$VERSION$method$API_ID$unit"
        network.httpRequest(activity.applicationContext, url, object: HttpResponse {
            override fun httpResponseSuccess(response: String) {
                var gson= Gson()
                var objectResonse=gson.fromJson(response, WeatherForecast::class.java)
                if(!objectResonse.list.isNullOrEmpty()) {
                    val nameCity = objectResonse.city.name!!
                    val forecastList = objectResonse.list!! as ArrayList<ListItem>
                    WeatherByNameInterface.getWeatherByName(nameCity,forecastList)
                }else{
                    WeatherByNameInterface.fail("Could not get Weather data")
                }
            }
        })
    }

    private fun makeIconURL(icon:String):String{
        val secion="img/w/"
        val url="$URL_BASE$secion$icon.png"
        return url
    }
}