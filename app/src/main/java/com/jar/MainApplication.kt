package com.jar

import android.app.Application
import android.content.Context
import com.jar.app.config.ApiConfig
import com.jar.app.di.useCaseModule
import com.jar.app.di.viewModelModule
import di.networkModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoinModule()
        appContext = applicationContext
    }

    private fun loadKoinModule() {
        startKoin {
            modules(networkModule(hostURL = ApiConfig.HOST))
            modules(viewModelModule)
            modules(useCaseModule)
        }
    }

    companion object {
        private lateinit var appContext: Context

        fun getAppContext(): Context = appContext
    }
}