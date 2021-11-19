package com.rtwotech.weather.domain.usecase

import com.rtwotech.weather.domain.repository.WeatherRepository

class GetWeatherUseCase (private val weatherRepository: WeatherRepository){

    fun getWeather(cityName: String?, lat: Double?, lon: Double?) =
        weatherRepository.getWeather(cityName, lat, lon)
}