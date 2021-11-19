package com.rtwotech.weather.presentation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.rtwotech.weather.R
import com.rtwotech.weather.databinding.ActivityMainBinding
import com.rtwotech.weather.domain.model.Weather
import com.rtwotech.weather.util.Status
import com.rtwotech.weather.util.StringFormatUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityMain : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val cancellationTokenSource = CancellationTokenSource()

    private var lat: Double? = null
    private var lon: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        getCurrentLocation()
        observeViewModel()
        initClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancellationTokenSource.cancel()
    }

    private fun observeViewModel() {
        viewModel.cities.observe(this) {
            initCitiesSpinner(it)
        }

        viewModel.weather.observe(this) {
            when (it.status) {
                Status.SUCCESS -> showWeather(it.data)
                Status.LOADING -> showLoading(true)
                Status.ERROR -> showError(it.message ?: "Error")
            }
        }
    }

    private fun initCitiesSpinner(cities: List<String>) {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.spinnerLocations.adapter = spinnerAdapter
    }

    private fun initClickListeners(){
        viewBinding.btnGetWeather.setOnClickListener {
            val selectedLocation = viewBinding.spinnerLocations.selectedItem.toString()
            if (!selectedLocation.equals("Current location", true)) {
                viewModel.getWeather(selectedLocation, lat, lon)
            } else {
                viewModel.getWeather(null, lat, lon)
            }
        }
    }

    private fun showWeather(weather: Weather?) {
        showLoading(false)
        if (weather != null) {
            viewBinding.tvWeatherData.text = StringFormatUtil.toHtmlSpannable(
                weather.toHtmlString())
        } else {
            viewBinding.tvWeatherData.text = ""
        }
    }

    private fun showLoading(show: Boolean) {
        if (show)
            viewBinding.progress.visibility = View.VISIBLE
        else
            viewBinding.progress.visibility = View.GONE
    }

    private fun showError(message: String) {
        showLoading(false)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentLocation() {
        if (viewModel.checkLocationPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener {
                lat = it.latitude
                lon = it.longitude
                showLocation()
            }
        }
    }

    private fun showLocation(){
        if (lat != null && lon != null){
            viewBinding.tvCurrentLocation.text = getString(R.string.current_location, lat, lon)
        } else {
            viewBinding.tvCurrentLocation.text = getString(R.string.current_location_unknown)
        }
    }
}