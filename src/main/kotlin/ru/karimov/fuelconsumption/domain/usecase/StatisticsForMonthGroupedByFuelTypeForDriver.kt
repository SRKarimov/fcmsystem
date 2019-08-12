package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.StatisticsForMonthGroupedByFuelTypeRepository

class StatisticsForMonthGroupedByFuelTypeForDriver (
    private val consumptionRepository: ConsumptionRepository,
    private val statisticsForMonthRepository: StatisticsForMonthGroupedByFuelTypeRepository
) {
    fun execute(driverId: Long, month: String): Map<String, Statistics> {
        val consumptions = consumptionRepository.fetchAll()
        return statisticsForMonthRepository.generate(driverId = driverId, month = month, consumptions = consumptions)
    }
}
