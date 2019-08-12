package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Purchase
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.usecase.repository.ListOfPurchasesForMonthRepository

class GetListOfPurchasesForMonth (
    private val consumptionRepository: ConsumptionRepository,
    private val listOfPurchasesRepository: ListOfPurchasesForMonthRepository
) {
    fun execute(month: String): List<Purchase> {
        val consumptions = consumptionRepository.fetchAll()
        return listOfPurchasesRepository.generate(month = month, consumptions = consumptions)
    }
}
