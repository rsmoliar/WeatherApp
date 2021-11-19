package com.rtwotech.weather.domain.usecase

import com.rtwotech.weather.domain.repository.CitiesRepository

class GetCitiesUseCase(private val citiesRepository: CitiesRepository) {

    fun getCities(allowCurrentLocation: Boolean) =
        citiesRepository.getCities(allowCurrentLocation)
}