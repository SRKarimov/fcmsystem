package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Purchase

interface ListOfPurchasesForMonthRepository {
    fun generate(month: String, consumptions: List<Consumption>): List<Purchase>
    fun generate(driverId: Long, month: String, consumptions: List<Consumption>): List<Purchase>
}
