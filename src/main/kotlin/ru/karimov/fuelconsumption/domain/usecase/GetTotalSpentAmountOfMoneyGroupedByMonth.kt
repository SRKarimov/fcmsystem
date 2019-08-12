package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.TotalSpentMoneyByMonthRepository

class GetTotalSpentAmountOfMoneyGroupedByMonth(
    private val consumptionRepository: ConsumptionRepository,
    private val totalSpentMoneyRepository: TotalSpentMoneyByMonthRepository
) {
    fun execute(): Map<String, Double> {
        val consumptions = consumptionRepository.fetchAll()
        return totalSpentMoneyRepository.generate(consumptions = consumptions)
    }
}
