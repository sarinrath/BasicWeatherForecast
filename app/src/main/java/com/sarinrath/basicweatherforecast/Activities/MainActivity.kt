package com.sarinrath.basicweatherforecast.Activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sarinrath.basicweatherforecast.Interfaces.WeatherByNameInterface
import com.sarinrath.basicweatherforecast.Model.OpenWeatherData
import com.sarinrath.basicweatherforecast.R

/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */
class MainActivity : AppCompatActivity(){

    var editTextCitySearch:EditText?=null

    var nameCity:String?=null
    var unit:String?=null

    var ivStatus: ImageView?=null
    var tvCity: TextView?=null
    var tvTemperature: TextView?=null
    var tvStatus: TextView?=null
    var tvDescription: TextView?=null
    var tvTempMin: TextView?=null
    var tvTempMax: TextView?=null
    var tvHumidity: TextView?=null

    var btnCelsius: Button?=null
    var btnFahrenheit: Button?=null
    var btnForecast: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        nameCity="bangkok"
        unit="&units=metric"
        getWeatherData(nameCity!!,unit!!)

        editTextCitySearch?.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH){
                if (editTextCitySearch?.text.toString().isNotEmpty()){
                    nameCity = editTextCitySearch?.text.toString()
                    unit="&units=metric"
                    if(nameCity.isNullOrBlank()){
                        Toast.makeText(this,"please fill city",Toast.LENGTH_LONG)
                    }else{
                        getWeatherData(nameCity!!, unit!!)
                    }
                }else{
                    Toast.makeText(this,"please fill city",Toast.LENGTH_LONG).show()
                }
                true
            } else {
                false
            }

        })


        btnCelsius?.setOnClickListener(){
            unit="&units=metric"
            if(nameCity!=null){
                getWeatherData(nameCity!!, unit!!)
            }else{
                Toast.makeText(this,"please fill city",Toast.LENGTH_LONG)
            }
        }

        btnFahrenheit?.setOnClickListener(){
            unit=""
            if(nameCity!=null){
                getWeatherData(nameCity!!, unit!!)
            }else{
                Toast.makeText(this,"please fill city",Toast.LENGTH_LONG)
            }
        }

        btnForecast?.setOnClickListener(){
            if(nameCity!=null){
                val intent= Intent(applicationContext, WeatherForecastActivity::class.java)
                intent.putExtra("CITY", nameCity)
                intent.putExtra("UNIT", unit)
                startActivity(intent)
            }else{
                Toast.makeText(this,"please fill city",Toast.LENGTH_LONG)
            }
        }

    }

    private fun initViews() {
        editTextCitySearch=findViewById(R.id.editTextCitySearch)
        tvCity=findViewById(R.id.tvCity)
        tvTemperature=findViewById(R.id.tvTemperature)
        tvTempMax=findViewById(R.id.tvTempMax)
        tvTempMin=findViewById(R.id.tvTempMin)
        tvHumidity=findViewById(R.id.tvHumidity)
        btnCelsius=findViewById(R.id.btnCelsius)
        btnFahrenheit=findViewById(R.id.btnFahrenheit)
        btnForecast=findViewById(R.id.btnForecast)
    }

    private fun getWeatherData(nameCity:String, unit:String){
        val openWeatherMap= OpenWeatherData(this)
        openWeatherMap.getWeatherByName(nameCity, unit, object: WeatherByNameInterface {
            override fun getWeatherByName(nameCity: String, urlImage: String, status: String, description: String,
                                          temperature: Double, tempMin: Double, tempMax: Double, humidity: Int) {
                this@MainActivity.runOnUiThread {
                    tvDescription?.text = description
                    tvCity?.text = nameCity
                    val m = if (!unit.isNullOrEmpty()) "°C" else "°F"

                    tvTemperature?.text = "Temperature: $temperature$m"
                    tvTempMax?.text = "Temperature max: $tempMax$m"
                    tvTempMin?.text = "Temperature min: $tempMin$m"
                    tvHumidity?.text = "Humidity: $humidity"
                }
            }

            override fun fail(msg: String) {
                this@MainActivity.runOnUiThread {
                    Toast.makeText(this@MainActivity,msg,Toast.LENGTH_LONG)?.show()
                }
            }
        })
    }

}