package com.example.spendingapp.features.balance.di

import com.example.spendingapp.features.balance.presentation.BalanceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val balanceModule = module {
    viewModel { BalanceViewModel(get()) }
}