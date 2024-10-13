package com.example.spendingapp.features.balance.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendingapp.core.data.repository.CoreRepository
import kotlinx.coroutines.launch

class BalanceViewModel(
    private val coreRepository: CoreRepository
): ViewModel() {

    var balanceState by mutableStateOf(BalanceState())
        private set

    init {
        viewModelScope.launch {
            balanceState = balanceState.copy(
                balance = coreRepository.getBalance()
            )
        }
    }


    fun onAction(balanceAction: BalanceAction) {
        when(balanceAction) {
            is BalanceAction.OnBalanceChanged -> {
                balanceState = balanceState.copy(
                    balance = balanceAction.balance
                )
            }
            BalanceAction.OnBalanceSaved -> {
                viewModelScope.launch {
                    coreRepository.updateBalance(balanceState.balance)
                }
            }
        }
    }


}















