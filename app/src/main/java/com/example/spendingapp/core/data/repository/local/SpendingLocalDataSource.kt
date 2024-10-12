package com.example.spendingapp.core.data.repository.local

import com.example.spendingapp.core.data.local.SpendingDao
import com.example.spendingapp.core.data.mapper.toSpending
import com.example.spendingapp.core.data.mapper.toSpendingEntity
import com.example.spendingapp.core.domain.model.Spending
import com.example.spendingapp.core.domain.repository.local.ISpendingLocalDataSource
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class SpendingLocalDataSource(
    private val dao: SpendingDao
): ISpendingLocalDataSource {

    override suspend fun getAllSpending(): List<Spending> {
        return dao.getAllSpending().map { it.toSpending() }
    }

    override suspend fun getSpendingByDate(dateTimeUtc: ZonedDateTime): List<Spending> {
        return dao.getAllSpending()
            .map { it.toSpending() }
            .filter {
                it.dateTimeUtc.dayOfWeek == dateTimeUtc.dayOfWeek
                        && it.dateTimeUtc.dayOfMonth == dateTimeUtc.dayOfMonth
                        && it.dateTimeUtc.dayOfYear == dateTimeUtc.dayOfYear
            }
    }

    override suspend fun getAllDates(): List<ZonedDateTime> {
        val uniqueDates = mutableSetOf<ZonedDateTime>()
        return dao.getAllDates().map {
            Instant.parse(it).atZone(ZoneId.of("UTC"))
        }.filter {
            uniqueDates.add(it)
        }
    }

    override suspend fun getSpending(id: Int): Spending {
        return dao.getSpendingById(id).toSpending()
    }

    override suspend fun upsertSpending(spending: Spending) {
        return dao.upsertSpending(spending.toSpendingEntity())
    }

    override suspend fun getSpendingBalance(): Double {
        return dao.getSpendingBalance() ?: 0.0
    }

    override suspend fun deleteSpending(id: Int) {
        return dao.deleteSpendEntity(id)
    }
}