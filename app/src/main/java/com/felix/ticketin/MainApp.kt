package com.felix.ticketin

import android.app.Application
import com.felix.ticketin.di.databaseModule
import com.felix.ticketin.di.networkModule
import com.felix.ticketin.di.repositoryModule
import com.felix.ticketin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}