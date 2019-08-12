package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.entity.StatisticsForMonthGroupedByFuelTypeReport
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class StatisticsForMonthGroupedByFuelType(
    private val consumptionRepository: ConsumptionRepository
) {
    fun execute(month: String): Map<String, Statistics> {
        val consumptions = consumptionRepository.fetchAll()
        return StatisticsForMonthGroupedByFuelTypeReport(consumptions = consumptions).generate(month = month)
    }
}
