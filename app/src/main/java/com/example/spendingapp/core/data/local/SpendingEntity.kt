package com.example.spendingapp.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("spending_entity")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val spendingId: Int? = null,
    val name: String,
    val price: Double,
    val kilogram: Double,
    val quantity: Double,
    val dateTimeUtc: String
)









