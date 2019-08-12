package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.ListOfPurchasesForMonthReport
import ru.karimov.fuelconsumption.domain.entity.Purchase
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository

class GetListOfPurchasesForMonth (private val consumptionRepository: ConsumptionRepository) {
    fun execute(month: String): List<Purchase> {
        val consumptionList = consumptionRepository.fetchAll()
        return ListOfPurchasesForMonthReport(consumptionList).generate(month = month)
    }
}
