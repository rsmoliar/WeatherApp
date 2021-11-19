package com.rtwotech.weather.di

import com.rtwotech.weather.data.CitiesRepositoryImpl
import com.rtwotech.weather.data.WeatherRepositoryImpl
import com.rtwotech.weather.domain.repository.CitiesRepository
import com.rtwotech.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CitiesRepository> { CitiesRepositoryImpl }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}