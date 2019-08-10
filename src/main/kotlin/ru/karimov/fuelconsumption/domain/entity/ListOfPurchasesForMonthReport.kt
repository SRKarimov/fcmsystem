package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.entity.Dto.PurchaseDto

class ListOfPurchasesForMonthReport {
    fun generate(month: String): List<PurchaseDto> {
        val consumptions = ListOfConsumption().getConsumptions()
        return consumptions
            .filter { it.date?.month.toString().toLowerCase() == month.toLowerCase() }
            .map {
                PurchaseDto(
                    fuelType = it.fuelType.toString(),
                    volume = it.volume,
                    date = it.date.toString(),
                    price = it.pricePerLitter,
                    totalPrice = it.volume * it.pricePerLitter,
                    driverId = it.driver.id
                )
            }
    }

    fun generate(driverId: Long, month: String): List<PurchaseDto> = generate(month).filter { it.driverId == driverId }
}
