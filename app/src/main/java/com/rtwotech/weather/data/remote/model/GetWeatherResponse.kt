package com.rtwotech.weather.data.remote.model

import com.squareup.moshi.Json

data class GetWeatherResponse(

    @field:Json(name = "main")
    val main: WeatherApiModel?
)