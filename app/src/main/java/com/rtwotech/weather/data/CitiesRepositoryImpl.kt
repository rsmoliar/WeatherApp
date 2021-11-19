package com.rtwotech.weather.data

import com.rtwotech.weather.domain.repository.CitiesRepository

object CitiesRepositoryImpl : CitiesRepository {
    override fun getCities(allowCurrentLocation: Boolean): List<String> {
        val citiesList = mutableListOf<String>()

        citiesList.add("Kyiv")
        citiesList.add("London")
        citiesList.add("Toronto")
        citiesList.add("Berlin")
        citiesList.add("Tokyo")

        //ADD CURRENT LOCATION IF ALLOWED
        if (allowCurrentLocation)
            citiesList.add("Current location")

        return citiesList
    }
}