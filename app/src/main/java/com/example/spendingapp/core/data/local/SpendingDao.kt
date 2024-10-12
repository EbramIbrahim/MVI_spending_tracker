package com.example.spendingapp.core.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface SpendingDao {

    @Upsert
    suspend fun upsertSpending(spendingEntity: SpendingEntity)

    @Query("SELECT * FROM spending_entity")
    suspend fun getAllSpending(): List<SpendingEntity>

    @Query("SELECT * FROM spending_entity WHERE spendingId = :id")
    suspend fun getSpendingById(id: Int): SpendingEntity

    @Query("SELECT dateTimeUtc FROM spending_entity")
    suspend fun getAllDates(): List<String>

    @Query("SELECT SUM(price) FROM spending_entity")
    suspend fun getSpendingBalance(): Double?

    @Query("DELETE FROM spending_entity WHERE spendingId = :id")
    suspend fun deleteSpendEntity(id: Int)

}











