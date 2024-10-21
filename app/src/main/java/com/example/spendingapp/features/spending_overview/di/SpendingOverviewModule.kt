package com.example.spendingapp.features.spending_overview.di

import com.example.spendingapp.features.spending_overview.presentation.viewmodel.SpendingOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val spendingOverviewModule = module {
    viewModel { SpendingOverviewViewModel(get(), get()) }
}