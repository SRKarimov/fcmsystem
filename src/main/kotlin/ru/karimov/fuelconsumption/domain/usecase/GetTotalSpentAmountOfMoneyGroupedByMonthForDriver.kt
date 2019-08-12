package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.ListOfConsumption
import ru.karimov.fuelconsumption.domain.usecase.impl.TotalSpentMoneyByMonthReport
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(private val consumptionRepository: ConsumptionRepository){
    fun execute(driverId: Long): Map<String, Double> =
        TotalSpentMoneyByMonthReport(ListOfConsumption().getConsumptions()).generate(driverId = driverId)
}
