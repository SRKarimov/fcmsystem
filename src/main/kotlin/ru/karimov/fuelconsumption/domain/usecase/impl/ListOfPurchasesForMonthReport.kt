package ru.karimov.fuelconsumption.domain.usecase.impl

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Purchase
import ru.karimov.fuelconsumption.domain.repository.ConsumptionRepository
import ru.karimov.fuelconsumption.domain.repository.ListOfPurchasesForMonthRepository

class ListOfPurchasesForMonthReport(private val consumptions: ConsumptionRepository): ListOfPurchasesForMonthRepository {
    override fun generate(month: String): List<Purchase> {
        return consumptions
            .filter { it.date?.month.toString().toLowerCase() == month.toLowerCase() }
            .map {
                Purchase(
                    fuelType = it.fuelType.toString(),
                    volume = it.volume,
                    date = it.date.toString(),
                    price = it.pricePerLitter,
                    totalPrice = it.volume * it.pricePerLitter,
                    driverId = it.driver.id
                )
            }
    }

    override fun generate(driverId: Long, month: String): List<Purchase> = generate(month).filter { it.driverId == driverId }
}
