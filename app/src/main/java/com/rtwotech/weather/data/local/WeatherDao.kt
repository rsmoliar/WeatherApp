package com.rtwotech.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherRecord(weatherItem: WeatherDbModel)

    @Query("SELECT * FROM tbl_weather WHERE cityName=:cityName LIMIT 1")
    suspend fun getWeatherRecord(cityName: String) : WeatherDbModel

    @Query("SELECT EXISTS (SELECT * FROM tbl_weather WHERE cityName=:cityName)")
    suspend fun isRecordExists(cityName: String) : Boolean
}