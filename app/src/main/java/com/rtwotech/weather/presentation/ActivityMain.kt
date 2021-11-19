package com.rtwotech.weather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rtwotech.weather.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}