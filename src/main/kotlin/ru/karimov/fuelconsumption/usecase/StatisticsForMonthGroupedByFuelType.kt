package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.entity.StatisticsForMonthGroupedByFuelTypeReport
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository

class StatisticsForMonthGroupedByFuelType(
    private val consumptionRepository: ConsumptionRepository
) {
    fun execute(month: String): Map<String, Statistics> {
        val consumptionList = consumptionRepository.fetchAll()
        return StatisticsForMonthGroupedByFuelTypeReport(consumptions = consumptionList).generate(month = month)
    }
}
