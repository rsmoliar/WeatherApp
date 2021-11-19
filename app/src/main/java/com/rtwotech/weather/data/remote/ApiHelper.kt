package com.rtwotech.weather.data.remote

import com.rtwotech.weather.data.remote.model.GetWeatherResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeather(city: String?, lat: Double?, lon: Double?): Response<GetWeatherResponse>
}