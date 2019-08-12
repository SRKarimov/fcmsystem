package ru.karimov.fuelconsumption.domain.entity

class TotalSpentMoneyByMonthReport(private val consumptions: List<Consumption>) {
    fun generate(): Map<String, Double> {
        return consumptions
            .groupBy { it.date?.month.toString() }
            .mapValues { it -> it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }

    fun generate(driverId: Long): Map<String, Double> {
        return consumptions
            .filter { it.driver.id == driverId }
            .groupBy { it.date?.month.toString() }
            .mapValues { it -> it.value.sumByDouble { it.pricePerLitter * it.volume } }
    }
}
