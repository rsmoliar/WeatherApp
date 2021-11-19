package com.rtwotech.weather.data.remote.model

import com.squareup.moshi.Json

data class WeatherApiModel(
    @field:Json(name = "feels_like")
    val feelsLike: Double?,
    @field:Json(name = "humidity")
    val humidity: Int?,
    @field:Json(name = "pressure")
    val pressure: Int?,
    @field:Json(name = "temp")
    val temp: Double?,
    @field:Json(name = "temp_max")
    val tempMax: Double?,
    @field:Json(name = "temp_min")
    val tempMin: Double?
)