package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.entity.Dto.PurchaseDto
import java.time.LocalDate

class TotalSpentMoneyByMonthReport {
    fun generate(): Map<String, List<PurchaseDto>> {
        val consumptions = ListOfConsumption().getConsumptions()
        return consumptions
            .map {
                PurchaseDto(
                    fuelType = it.fuelType.toString(),
                    volume = it.volume,
                    date = it.date.toString(),
                    price = it.pricePerLitter,
                    totalPrice = it.volume * it.pricePerLitter,
                    driverId = it.driver.id
                )
            }.groupBy { it -> LocalDate.parse(it.date).month.toString() }
    }

    fun generate(driverId: Long, month: String): List<PurchaseDto> = TODO()
}
