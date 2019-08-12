package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.TotalSpentMoneyByMonthReport
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetTotalSpentAmountOfMoneyGroupedByMonth(
    private val consumptionRepository: ConsumptionRepository
) {
    fun execute(): Map<String, Double> {
        val consumptions = consumptionRepository.fetchAll()
        return TotalSpentMoneyByMonthReport(consumptions = consumptions).generate()
    }
}
