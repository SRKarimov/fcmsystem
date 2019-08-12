package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Statistics

interface StatisticsForMonthGroupedByFuelTypeRepository {
    fun generate(month: String, consumptions: List<Consumption>): Map<String, Statistics>
    fun generate(driverId: Long, month: String, consumptions: List<Consumption>): Map<String, Statistics>
}
