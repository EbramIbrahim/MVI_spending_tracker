package com.example.spendingapp.core.domain.repository

interface ICoreRepository {

    suspend fun updateBalance(balance: Double)

    suspend fun getBalance(): Double
}