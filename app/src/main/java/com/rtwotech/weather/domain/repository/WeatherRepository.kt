package com.rtwotech.weather.domain.repository

import com.rtwotech.weather.domain.model.Weather

interface WeatherRepository {

    fun getWeather(cityName: String?, lat: Double?, lon: Double?): Weather
}