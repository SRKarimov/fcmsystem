package ru.karimov.fuelconsumption.domain.usecase.impl

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Statistics
import ru.karimov.fuelconsumption.domain.repository.StatisticsForMonthGroupedByFuelTypeRepository

class StatisticsForMonthGroupedByFuelTypeReport(private val consumptions: List<Consumption>):
    StatisticsForMonthGroupedByFuelTypeRepository {
    override fun generate(month: String): Map<String, Statistics> {
        val records =
            consumptions
                .filter { it.date?.month.toString().toLowerCase() == month.toLowerCase() }
                .groupBy { it.fuelType.toString() }

        val stat: MutableMap<String, Statistics> = mutableMapOf()
        for (item in records) {
            stat[item.key] = getStatistics(item.value)
        }

        return stat
    }

    override fun generate(driverId: Long, month: String): Map<String, Statistics> {
        val records =
            consumptions
                .filter { it.driver.id == driverId && it.date?.month.toString().toLowerCase() == month.toLowerCase() }
                .groupBy { it.fuelType.toString() }

        val stat: MutableMap<String, Statistics> = mutableMapOf()
        for (item in records) {
            stat[item.key] = getStatistics(item.value)
        }

        return stat
    }

    private fun getStatistics(items: List<Consumption>) =
        Statistics(
            volume = items.sumByDouble { it.volume },
            averagePrice = items.sumByDouble { it.pricePerLitter } / items.size,
            totalPrice = items.sumByDouble { (it.pricePerLitter * it.volume) }
        )
}
