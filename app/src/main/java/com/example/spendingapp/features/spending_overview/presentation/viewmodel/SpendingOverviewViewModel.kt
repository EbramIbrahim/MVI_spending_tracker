package com.example.spendingapp.features.spending_overview.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendingapp.core.data.repository.CoreRepository
import com.example.spendingapp.core.data.repository.local.SpendingLocalDataSource
import com.example.spendingapp.core.domain.model.Spending
import com.example.spendingapp.features.spending_overview.presentation.util.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class SpendingOverviewViewModel(
    private val spendingDataSource: SpendingLocalDataSource,
    private val coreRepository: CoreRepository
) : ViewModel() {


    var state by mutableStateOf(SpendingOverviewState())
        private set


    fun onAction(action: SpendingOverviewAction) {
        when (action) {
            SpendingOverviewAction.LoadingSpendingOverviewAndBalance -> {
                loadSpendingListAndBalance()
            }

            is SpendingOverviewAction.OnDateChanged -> {
                val newDate = state.dateList[action.newDate]
                viewModelScope.launch {
                    state = state.copy(
                        pickedDateTime = newDate,
                        spendingList = getSpendingByDate(newDate)
                        )
                }
            }

            is SpendingOverviewAction.OnDataDeleted -> {
                viewModelScope.launch {
                    spendingDataSource.deleteSpending(action.spendingId)
                    state = state.copy(
                        spendingList = getSpendingByDate(state.pickedDateTime),
                        dateList = spendingDataSource.getAllDates(),
                        balance = coreRepository.getBalance() - spendingDataSource.getSpendingBalance()
                    )
                }
            }
        }
    }


    private fun loadSpendingListAndBalance() {
        viewModelScope.launch {
            val allDates = spendingDataSource.getAllDates()

            state = state.copy(
                spendingList = getSpendingByDate(
                    allDates.lastOrNull() ?: ZonedDateTime.now()
                ),
                balance = coreRepository.getBalance() - spendingDataSource.getSpendingBalance(),
                pickedDateTime = allDates.lastOrNull() ?: ZonedDateTime.now(),
                dateList = allDates.reversed()
            )
        }
    }


    private suspend fun getSpendingByDate(date: ZonedDateTime): List<Spending> {
        return spendingDataSource
            .getSpendingByDate(date)
            .reversed()
            .map { it.copy(color = randomColor()) }
    }

}















