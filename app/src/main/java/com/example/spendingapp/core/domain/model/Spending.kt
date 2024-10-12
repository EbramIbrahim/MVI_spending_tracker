package com.example.spendingapp.core.domain.model

import java.time.ZonedDateTime


data class Spending(
    val spendingId: Int,
    val name: String,
    val price: Double,
    val kilogram: Double,
    val quantity: Double,
    val dateTimeUtc: ZonedDateTime,
    val color: Int = 0,
)









