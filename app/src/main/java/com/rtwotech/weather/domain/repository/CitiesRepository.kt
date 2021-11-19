package com.rtwotech.weather.domain.repository

interface CitiesRepository {

    fun getCities(allowCurrentLocation: Boolean) : List<String>
}