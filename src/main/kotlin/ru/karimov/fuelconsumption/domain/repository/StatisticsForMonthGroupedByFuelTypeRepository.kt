package ru.karimov.fuelconsumption.domain.repository

import ru.karimov.fuelconsumption.domain.entity.Statistics

interface StatisticsForMonthGroupedByFuelTypeRepository {
    fun generate(month: String): Map<String, Statistics>
    fun generate(driverId: Long, month: String): Map<String, Statistics>
}
