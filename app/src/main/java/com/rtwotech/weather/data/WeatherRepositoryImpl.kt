package com.rtwotech.weather.data

import com.rtwotech.weather.data.local.WeatherDao
import com.rtwotech.weather.data.mapper.WeatherDataMapper
import com.rtwotech.weather.data.remote.ApiHelper
import com.rtwotech.weather.domain.model.Weather
import com.rtwotech.weather.domain.repository.WeatherRepository
import com.rtwotech.weather.util.Resource

class WeatherRepositoryImpl(private val apiHelper: ApiHelper, private val weatherDao: WeatherDao)
    : WeatherRepository {

    private val mapper = WeatherDataMapper()

    override suspend fun getWeather(cityName: String?, lat: Double?, lon: Double?):
            Resource<Weather> {
        //null value in cityName means CURRENT LOCATION
        val cityNameRecord = cityName ?: "_current"
        val recordExists = weatherDao.isRecordExists(cityNameRecord)
        if (!recordExists){
            apiHelper.getWeather(cityName, lat, lon).let {
                return if (it.isSuccessful && it.body() != null && it.body()?.main != null){
                    val weather = mapper.weatherApiModelToWeather(it.body()?.main)
                    saveWeather(cityNameRecord, lat, lon, weather)
                    Resource.onSuccess(weather)
                } else {
                    Resource.onError(null, it.message())
                }
            }
        } else {
            val weatherDbRecord = weatherDao.getWeatherRecord(cityNameRecord)
            return Resource.onSuccess(mapper.weatherDbModelToWeather(weatherDbRecord))
        }
    }

    private suspend fun saveWeather(cityName: String?, lat: Double?, lon: Double?, weather: Weather){
        weatherDao.addWeatherRecord(mapper.weatherToWeatherDbModel(cityName, lat, lon, weather))
    }
}