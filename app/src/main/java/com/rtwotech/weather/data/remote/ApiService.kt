package com.rtwotech.weather.data.remote

import com.rtwotech.weather.data.remote.model.GetWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeather(@Query("appid") appId: String,
                           @Query("q") city: String?,
                           @Query("lat") lat: Double?,
                           @Query("lon") lon: Double?,
                           @Query("units") units: String): Response<GetWeatherResponse>
}