package com.rtwotech.weather.di

import com.rtwotech.weather.presentation.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    //Providing view module with injected repository
    viewModel { MainViewModel(androidApplication(), get(), get()) }
}