package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetTotalSpentAmountOfMoneyGroupedByMonth(private val consumptionRepository: ConsumptionRepository) {
    fun execute(month: String): Map<String, Double> = TODO()
}
