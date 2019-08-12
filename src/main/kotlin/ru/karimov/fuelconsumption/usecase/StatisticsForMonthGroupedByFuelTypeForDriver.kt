package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.entity.StatisticsForMonthGroupedByFuelTypeReport
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.usecase.repository.DriverRepository

class StatisticsForMonthGroupedByFuelTypeForDriver (
    private val consumptionRepository: ConsumptionRepository,
    private val driverRepository: DriverRepository
) {
    fun execute(driverId: Long, month: String): Map<String, Statistics> {
        val consumptions = consumptionRepository.fetchAll()
        val driver = driverRepository.fetchById(driverId)
        return StatisticsForMonthGroupedByFuelTypeReport(consumptions = consumptions).generate(driver = driver, month = month)
    }
}
