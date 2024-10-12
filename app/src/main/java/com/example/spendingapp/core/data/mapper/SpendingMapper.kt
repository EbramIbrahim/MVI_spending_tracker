package com.example.spendingapp.core.data.mapper

import com.example.spendingapp.core.data.local.SpendingDao
import com.example.spendingapp.core.data.local.SpendingEntity
import com.example.spendingapp.core.domain.model.Spending
import java.time.Instant
import java.time.ZoneId


fun SpendingEntity.toSpending(): Spending {
    return Spending(
        spendingId = spendingId ?: 0,
        name = name,
        price = price,
        kilogram = kilogram,
        quantity = quantity,
        dateTimeUtc = Instant.parse(dateTimeUtc).atZone(ZoneId.of("UTC"))
    )
}


fun Spending.toSpendingEntity(): SpendingEntity {
    return SpendingEntity(
        name = name,
        price = price,
        kilogram = kilogram,
        quantity = quantity,
        dateTimeUtc = dateTimeUtc.toInstant().toString()
    )
}















