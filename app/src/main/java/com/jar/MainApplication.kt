package com.jar

import android.app.Application
import com.jar.app.config.ApiConfig
import di.networkModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModule()
    }

    private fun loadKoinModule() {
        startKoin {
            modules(networkModule(hostURL = ApiConfig.HOST))
        }
    }
}