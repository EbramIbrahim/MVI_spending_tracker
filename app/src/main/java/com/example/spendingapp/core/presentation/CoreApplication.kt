package com.example.spendingapp.core.presentation

import android.app.Application
import com.example.spendingapp.core.di.coreModule
import com.example.spendingapp.features.balance.di.balanceModule
import com.example.spendingapp.features.spending_overview.di.spendingOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoreApplication)
            modules(
                coreModule,
                balanceModule,
                spendingOverviewModule
            )
        }

    }
}