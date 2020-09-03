package com.sarinrath.basicweatherforecast.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarinrath.basicweatherforecast.Adapters.WeatherForecastAdapter
import com.sarinrath.basicweatherforecast.Interfaces.WeatherForecastByNameInterface
import com.sarinrath.basicweatherforecast.Model.OpenWeatherData
import com.sarinrath.basicweatherforecast.Model.WeatherForecast.ListItem
import com.sarinrath.basicweatherforecast.R

/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */
class WeatherForecastActivity : AppCompatActivity(){

    var listItem:ArrayList<ListItem>?=null
    var nameCity:String?=null
    var unit:String?=null
    var weatherForecastAdapter: WeatherForecastAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weatherforecast)
        getExtras()
        getWeatherData(nameCity!!,unit!!)
    }

    private fun getExtras() {
        nameCity=intent.getStringExtra("CITY")
        unit=intent.getStringExtra("UNIT")
    }

    private fun getWeatherData(nameCity:String, unit:String){
        val openWeatherMap= OpenWeatherData(this)
        openWeatherMap.getWeatherForecastByName(nameCity, unit, object: WeatherForecastByNameInterface {
            override fun getWeatherByName(nameCity: String, list: ArrayList<ListItem>?) {
                this@WeatherForecastActivity.runOnUiThread {
                    listItem = list;
                    val m = if (!unit.isNullOrEmpty()) "°C" else "°F"
                    var layoutManager:RecyclerView.LayoutManager?=null
                    var listForecast: RecyclerView = findViewById(R.id.listForecast)
                    listForecast?.setHasFixedSize(true)
                    layoutManager= LinearLayoutManager(this@WeatherForecastActivity)
                    listForecast?.layoutManager=layoutManager
                    weatherForecastAdapter=WeatherForecastAdapter(m!!,listItem!!)
                    listForecast?.adapter=weatherForecastAdapter
                }
            }

            override fun fail(msg: String) {
                this@WeatherForecastActivity.runOnUiThread {
                    Toast.makeText(this@WeatherForecastActivity,msg,Toast.LENGTH_LONG)?.show()
                }
            }
        })
    }

}