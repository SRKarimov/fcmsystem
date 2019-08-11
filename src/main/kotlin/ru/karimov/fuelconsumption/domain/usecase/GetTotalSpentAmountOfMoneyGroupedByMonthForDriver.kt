package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(private val consumptionRepository: ConsumptionRepository){
    fun execute(driverId: Long, month: String): Map<String, Double> = TODO()
}
