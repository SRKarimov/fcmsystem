package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.repository.TotalSpentMoneyByMonthRepository

class GetTotalSpentAmountOfMoneyGroupedByMonth(
    private val repository: ConsumptionRepository,
    private val totalSpentMoneyByMonth: TotalSpentMoneyByMonthRepository
) {
    fun execute(): Map<String, Double> =
        totalSpentMoneyByMonth.generate()
}
