package com.rtwotech.weather

import android.app.Application
import com.rtwotech.weather.data.local.AppDB
import com.rtwotech.weather.di.dbModule
import com.rtwotech.weather.di.networkModule
import com.rtwotech.weather.di.repositoryModule
import com.rtwotech.weather.di.vmModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@WeatherApp)
            modules(networkModule, dbModule, repositoryModule, vmModule)
        }
        //CLEARING DB ON EVERY APP LAUNCH
        CoroutineScope(Dispatchers.IO).launch {
            clearDatabase()
        }
    }

    private fun clearDatabase(){
        val appDb: AppDB by inject()
        appDb.clearAllTables()
    }
}