package com.rtwotech.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherDbModel::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract val weatherDao: WeatherDao
}