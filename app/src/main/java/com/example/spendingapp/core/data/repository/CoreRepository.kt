package com.example.spendingapp.core.data.repository

import android.content.SharedPreferences
import com.example.spendingapp.core.domain.repository.ICoreRepository

class CoreRepository(
    private val prefs: SharedPreferences
) : ICoreRepository {

    override suspend fun updateBalance(balance: Double) {
        prefs.edit().putFloat(BALANCE_KEY, balance.toFloat()).apply()
    }

    override suspend fun getBalance(): Double {
        return prefs.getFloat(BALANCE_KEY, 0f).toDouble()
    }


    companion object {
        const val BALANCE_KEY = "balance_key"
    }

}