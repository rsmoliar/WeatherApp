package com.rtwotech.weather.domain.repository

import com.rtwotech.weather.domain.model.Weather
import com.rtwotech.weather.util.Resource

interface WeatherRepository {

    suspend fun getWeather(cityName: String?, lat: Double?, lon: Double?): Resource<Weather>
}