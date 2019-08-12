package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.ListOfPurchasesForMonthReport
import ru.karimov.fuelconsumption.domain.entity.Purchase
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.usecase.repository.DriverRepository

class GetListOfPurchasesForMonthAndDriver(
    private val consumptionRepository: ConsumptionRepository,
    private val driverRepository: DriverRepository
    ) {
    fun execute(driverId: Long, month: String): List<Purchase> {
        val consumptionList = consumptionRepository.fetchAll()
        val driver = driverRepository.fetchById(driverId)
        return ListOfPurchasesForMonthReport(consumptionList).generate(driver = driver, month = month)
    }
}
