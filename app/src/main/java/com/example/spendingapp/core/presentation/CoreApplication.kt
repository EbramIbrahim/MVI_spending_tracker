package com.example.spendingapp.core.presentation

import android.app.Application
import com.example.spendingapp.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoreApplication)
            modules(
                coreModule
            )
        }

    }
}