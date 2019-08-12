package ru.karimov.fuelconsumption.domain.repository

interface TotalSpentMoneyByMonthRepository {
    fun generate(): Map<String, Double>
    fun generate(driverId: Long): Map<String, Double>
}
