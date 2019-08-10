package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.entity.Dto.Purchase

class ListOfPurchasesForMonthReport {
    fun generate(month: String): List<Purchase> {
        val consumptions = ListOfConsumption().getConsumptions()
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

    fun generate(driverId: Long, month: String): List<Purchase> = generate(month).filter { it.driverId == driverId }
}
