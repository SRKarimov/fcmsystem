package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.TotalSpentMoneyByMonthReport
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.TotalSpentMoneyByMonthRepository

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
    private val consumptionRepository: ConsumptionRepository,
    private val totalSpentMoneyRepository: TotalSpentMoneyByMonthRepository
    ) {
        fun execute(driverId: Long): Map<String, Double> {
            val consumptions = consumptionRepository.fetchAll()
            return totalSpentMoneyRepository.generate(driverId = driverId, consumptions = consumptions)
        }
}
