package com.example.spendingapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SpendingEntity::class], version = 1)
abstract class SpendingDataBase: RoomDatabase() {
    abstract val spendingDao: SpendingDao
}