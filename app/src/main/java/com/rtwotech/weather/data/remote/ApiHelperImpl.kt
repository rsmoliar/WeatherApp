package com.rtwotech.weather.data.remote

import com.rtwotech.weather.BuildConfig

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeather(
        city: String?,
        lat: Double?,
        lon: Double?
    ) = apiService.getWeather(BuildConfig.APP_ID, city, lat, lon, UNITS_METRIC)

    companion object{
        private const val UNITS_METRIC = "metric"
    }
}