package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.StatisticsForMonthGroupedByFuelTypeRepository

class StatisticsForMonthGroupedByFuelType(
    private val consumptionRepository: ConsumptionRepository,
    private val statisticsForMonthRepository: StatisticsForMonthGroupedByFuelTypeRepository
) {
    fun execute(month: String): Map<String, Statistics> {
        val consumptions = consumptionRepository.fetchAll()
        return statisticsForMonthRepository.generate(month = month, consumptions = consumptions)
    }
}
