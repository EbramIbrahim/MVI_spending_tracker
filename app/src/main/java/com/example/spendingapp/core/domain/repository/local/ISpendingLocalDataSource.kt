package com.example.spendingapp.core.domain.repository.local

import com.example.spendingapp.core.domain.model.Spending
import java.time.ZonedDateTime

interface ISpendingLocalDataSource {

    suspend fun getAllSpending(): List<Spending>

    suspend fun getSpendingByDate(dateTimeUtc: ZonedDateTime): List<Spending>

    suspend fun getAllDates(): List<ZonedDateTime>

    suspend fun getSpending(id: Int): Spending

    suspend fun upsertSpending(spending: Spending)

    suspend fun getSpendingBalance(): Double

    suspend fun deleteSpending(id: Int)
}