package com.rtwotech.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_weather")
data class WeatherDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityName: String?,
    val lat: Double?,
    val lon: Double?,
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?
)