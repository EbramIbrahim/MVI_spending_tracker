package com.example.spendingapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.spendingapp.core.data.local.SpendingDataBase
import com.example.spendingapp.core.data.repository.CoreRepository
import com.example.spendingapp.core.data.repository.local.SpendingLocalDataSource
import com.example.spendingapp.core.domain.repository.ICoreRepository
import com.example.spendingapp.core.domain.repository.local.ISpendingLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
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
    singleOf(::SpendingLocalDataSource).bind<ISpendingLocalDataSource>()
    singleOf(::CoreRepository).bind<ICoreRepository>()
}









