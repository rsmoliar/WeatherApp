package com.rtwotech.weather.data.mapper

import com.rtwotech.weather.data.local.WeatherDbModel
import com.rtwotech.weather.data.remote.model.WeatherApiModel
import com.rtwotech.weather.domain.model.Weather

class WeatherDataMapper {

    fun weatherApiModelToWeather(weatherApiModel: WeatherApiModel?)
            : Weather = Weather(
        temp = weatherApiModel?.temp,
        feelsLike = weatherApiModel?.feelsLike,
        tempMin = weatherApiModel?.tempMin,
        tempMax = weatherApiModel?.tempMax,
        pressure = weatherApiModel?.pressure,
        humidity = weatherApiModel?.humidity
    )

    fun weatherToWeatherDbModel(cityName: String?, lat: Double?, lon: Double?, weather: Weather)
            : WeatherDbModel = WeatherDbModel(
        cityName = cityName,
        lat = lat,
        lon = lon,
        temp = weather.temp,
        tempMax = weather.tempMax,
        tempMin = weather.tempMin,
        feelsLike = weather.feelsLike,
        pressure = weather.pressure,
        humidity = weather.humidity
    )

    fun weatherDbModelToWeather(weatherDbModel: WeatherDbModel): Weather =
        Weather(
            temp = weatherDbModel.temp,
            feelsLike = weatherDbModel.feelsLike,
            tempMin = weatherDbModel.tempMin,
            tempMax = weatherDbModel.tempMax,
            pressure = weatherDbModel.pressure,
            humidity = weatherDbModel.humidity
        )
}