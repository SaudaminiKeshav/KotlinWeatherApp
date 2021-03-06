package com.androidapp.kotlinweatherapp.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.androidapp.kotlinweatherapp.R
import com.androidapp.kotlinweatherapp.model.City
import com.androidapp.kotlinweatherapp.model.CityList
import com.androidapp.kotlinweatherapp.model.WeatherList
import com.androidapp.kotlinweatherapp.presenter.WeatherInteractorImpl
import com.androidapp.kotlinweatherapp.presenter.WeatherPresenter
import kotlinx.android.synthetic.main.fragment_weather_detail.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class WeatherDetailFragment : Fragment(), WeatherView{

    var zipCode = ""
    var spinnerSelection = ""
    lateinit var weatherInteractor: WeatherInteractorImpl
    lateinit var presenter: WeatherPresenter
    lateinit var manager: GridLayoutManager

    companion object {
        val appid = "9d02799fe291e3840cda7eb24fcfd2a1"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather_detail, container, false)

        arguments?.let {
            zipCode = it.getString("ZIPCODE")
            spinnerSelection = it.getString("SPINNER")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherInteractor = WeatherInteractorImpl()
        presenter = WeatherPresenter(weatherInteractor, this, context!!.applicationContext)
        presenter.getWeatherData(zipCode, appid)
    }

    override fun showWeatherData(cityList: CityList,
                                 weatherList: List<WeatherList>?,
                                 city: City) {

        manager = GridLayoutManager(this.context,3)
        rv_weather.layoutManager = manager
        rv_weather.adapter = WeatherAdapter(weatherList,spinnerSelection)

    }
}
