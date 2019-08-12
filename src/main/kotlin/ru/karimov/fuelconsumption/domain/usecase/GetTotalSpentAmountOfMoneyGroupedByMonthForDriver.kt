package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.TotalSpentMoneyByMonthReport
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.DriverRepository

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
    private val consumptionRepository: ConsumptionRepository,
    private val driverRepository: DriverRepository
    ) {
        fun execute(driverId: Long): Map<String, Double> {
            val consumptions = consumptionRepository.fetchAll()
            val driver = driverRepository.fetchById(driverId)
            return TotalSpentMoneyByMonthReport(consumptions = consumptions).generate(driver = driver)
        }
}
