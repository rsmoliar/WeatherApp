package com.rtwotech.weather.di

import android.app.Application
import androidx.room.Room
import com.rtwotech.weather.data.local.AppDB
import com.rtwotech.weather.data.local.WeatherDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single { provideDb(androidApplication()) }
    single { provideDao(get()) }
}

fun provideDb(application: Application): AppDB {
    return Room.databaseBuilder(
        application,
        AppDB::class.java,
        "db_weather"
    ).build()
}

fun provideDao(appDB: AppDB): WeatherDao {
    return appDB.weatherDao
}