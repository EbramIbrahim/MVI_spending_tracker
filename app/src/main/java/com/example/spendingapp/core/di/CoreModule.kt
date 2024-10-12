package com.example.spendingapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.spendingapp.core.data.local.SpendingDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendingDataBase::class.java,
            "spending_database"
        ).build()
    }

    single { get<SpendingDataBase>().spendingDao }


    single {
        androidApplication().getSharedPreferences(
            "spending_tracker_preference", Context.MODE_PRIVATE
        )
    }


}









