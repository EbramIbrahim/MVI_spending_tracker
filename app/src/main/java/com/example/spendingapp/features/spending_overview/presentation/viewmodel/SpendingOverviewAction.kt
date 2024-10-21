package com.example.spendingapp.features.spending_overview.presentation.viewmodel

sealed interface SpendingOverviewAction {

    data object LoadingSpendingOverviewAndBalance: SpendingOverviewAction

    data class OnDataChanged(val spendingId: Int): SpendingOverviewAction

    data class OnDataDeleted(val spendingId: Int): SpendingOverviewAction

}