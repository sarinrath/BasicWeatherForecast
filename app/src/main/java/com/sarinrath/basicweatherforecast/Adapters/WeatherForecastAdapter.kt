package com.sarinrath.basicweatherforecast.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sarinrath.basicweatherforecast.Model.WeatherForecast.ListItem
import com.sarinrath.basicweatherforecast.R

/**
 * Created by sarinrathprachitkhornburi on 2/9/2020 AD.
 * sarinrath
 * sarinrath.pr@gmail.com
 */

class WeatherForecastAdapter( unit:String, items: ArrayList<ListItem>) : RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    var items: ArrayList<ListItem>? = null
    var unit: String?=null

    init {
        this.items = items
        this.unit = unit
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items_weather, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvDateTime?.text = items?.get(position)?.dt_txt
        viewHolder.tvTemp?.text = "Temperature: "+items?.get(position)?.main?.temp.toString()+unit
        viewHolder.tvHumidity?.text = "Humidity: "+items?.get(position)?.main?.humidity.toString()
    }

    override fun getItemCount(): Int {
        return items!!.count()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDateTime: TextView? = null
        var tvTemp: TextView? = null
        var tvHumidity: TextView? = null

        init {
            tvDateTime = itemView.findViewById(R.id.tvDateTime)
            tvTemp = itemView.findViewById(R.id.tvTemp)
            tvHumidity = itemView.findViewById(R.id.tvHumidity)

        }
    }
}
