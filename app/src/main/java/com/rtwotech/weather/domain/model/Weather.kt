package com.rtwotech.weather.domain.model

import kotlin.math.roundToInt

data class Weather(
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?
){
    fun toHtmlString() : String {
        return "<b>Temperature:</b> ${temp?.roundToInt()} &#8451;<br>" +
                "<b>Feels like:</b> ${feelsLike?.roundToInt()} &#8451;<br>" +
                "<b>Temperature min:</b> ${tempMin?.roundToInt()} &#8451;<br>" +
                "<b>Temperature max:</b> ${tempMax?.roundToInt()} &#8451;<br>" +
                "<b>Pressure:</b> $pressure hPa<br>" +
                "<b>Humidity:</b> $humidity %"
    }
}
