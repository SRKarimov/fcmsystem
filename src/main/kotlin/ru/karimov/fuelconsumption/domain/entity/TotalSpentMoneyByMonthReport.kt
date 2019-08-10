package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.entity.Dto.PurchaseDto
import java.time.LocalDate

class TotalSpentMoneyByMonthReport {
    fun generate(): Map<String, Double> {
        val consumptions = ListOfConsumption().getConsumptions()
        return consumptions
            .groupBy { it.date?.month.toString() }
            .mapValues { it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }

    fun generate(driverId: Long): Map<String, Double> {
        val consumptions = ListOfConsumption().getConsumptions()
        return consumptions
            .filter { it.driver.id == driverId }
            .groupBy { it.date?.month.toString() }
            .mapValues { it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }
}
