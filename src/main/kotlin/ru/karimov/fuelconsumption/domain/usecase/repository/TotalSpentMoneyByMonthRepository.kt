package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption

interface TotalSpentMoneyByMonthRepository {
    fun generate(consumptions: List<Consumption>): Map<String, Double>
    fun generate(driverId: Long, consumptions: List<Consumption>): Map<String, Double>
}
