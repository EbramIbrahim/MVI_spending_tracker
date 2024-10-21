package com.example.spendingapp.features.spending_overview.presentation.viewmodel

import com.example.spendingapp.core.domain.model.Spending
import java.time.ZonedDateTime

data class SpendingOverviewState(
    val spendingList: List<Spending> = emptyList(),
    val dateList: List<ZonedDateTime> = emptyList(),
    val balance: Double = 0.0,
    val pickedDateTime: ZonedDateTime = ZonedDateTime.now(),
    val isDropDownMenuVisible: Boolean = false
)
