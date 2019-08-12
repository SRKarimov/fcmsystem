package ru.karimov.fuelconsumption.domain.repository

import ru.karimov.fuelconsumption.domain.entity.Purchase

interface ListOfPurchasesForMonthRepository {
    fun generate(month: String): List<Purchase>
    fun generate(driverId: Long, month: String): List<Purchase>
}