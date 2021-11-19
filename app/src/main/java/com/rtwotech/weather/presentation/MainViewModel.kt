package com.rtwotech.weather.presentation

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rtwotech.weather.domain.model.Weather
import com.rtwotech.weather.domain.repository.CitiesRepository
import com.rtwotech.weather.domain.repository.WeatherRepository
import com.rtwotech.weather.domain.usecase.GetCitiesUseCase
import com.rtwotech.weather.domain.usecase.GetWeatherUseCase
import com.rtwotech.weather.util.Resource
import kotlinx.coroutines.launch

class MainViewModel (
    application: Application,
    citiesRepository : CitiesRepository,
    weatherRepository: WeatherRepository
): AndroidViewModel(application) {

    private val getCitiesUseCase = GetCitiesUseCase(citiesRepository)
    private val getWeatherUseCase =  GetWeatherUseCase(weatherRepository)

    private val _cities = MutableLiveData<List<String>>()
    val cities: LiveData<List<String>>
        get() = _cities

    private val _weather = MutableLiveData<Resource<Weather>>()
    val weather: LiveData<Resource<Weather>>
        get() = _weather

    init {
        getCities()
    }

    private fun getCities(){
        _cities.value = getCitiesUseCase.getCities(checkLocationPermission())
    }

    fun getWeather(city: String?, lat: Double?, lon: Double?){
        viewModelScope.launch {
            _weather.postValue(Resource.onLoading(null))
            _weather.postValue(getWeatherUseCase.getWeather(city, lat, lon))
        }
    }

    fun checkLocationPermission() : Boolean{
        //Check location permission before access location and get cities data
        return ActivityCompat.checkSelfPermission(
            getApplication(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            getApplication(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}