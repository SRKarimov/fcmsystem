package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.TotalSpentMoneyByMonthReport
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.usecase.repository.DriverRepository

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
    private val consumptionRepository: ConsumptionRepository,
    private val driverRepository: DriverRepository
    ) {
        fun execute(driverId: Long): Map<String, Double> {
            val consumptionList = consumptionRepository.fetchAll()
            val driver = driverRepository.fetchById(driverId)
            return TotalSpentMoneyByMonthReport(consumptions = consumptionList).generate(driver = driver)
        }
}
