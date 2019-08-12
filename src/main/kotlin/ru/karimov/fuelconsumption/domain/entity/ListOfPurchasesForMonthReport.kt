package ru.karimov.fuelconsumption.domain.entity

class ListOfPurchasesForMonthReport(private val consumptions: List<Consumption>) {
    fun generate(month: String): List<Purchase> {
        require(consumptions.isNotEmpty()) { "List of consumptions should not be empty" }
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
