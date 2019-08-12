package ru.karimov.fuelconsumption.domain.usecase.impl

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.repository.TotalSpentMoneyByMonthRepository

class TotalSpentMoneyByMonthReport(private val consumptions: List<Consumption>): TotalSpentMoneyByMonthRepository {
    override fun generate(): Map<String, Double> {
        return consumptions
            .groupBy { it.date?.month.toString() }
            .mapValues { it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }

    override fun generate(driverId: Long): Map<String, Double> {
        return consumptions
            .filter { it.driver.id == driverId }
            .groupBy { it.date?.month.toString() }
            .mapValues { it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }
}
