package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.ListOfPurchasesForMonthReport
import ru.karimov.fuelconsumption.domain.entity.Purchase
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetListOfPurchasesForMonth (private val consumptionRepository: ConsumptionRepository) {
    fun execute(month: String): List<Purchase> {
        val consumptions = consumptionRepository.fetchAll()
        return ListOfPurchasesForMonthReport(consumptions).generate(month = month)
    }
}
