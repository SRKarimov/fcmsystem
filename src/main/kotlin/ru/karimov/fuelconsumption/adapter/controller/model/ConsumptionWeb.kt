package ru.karimov.fuelconsumption.adapter.controller.model

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import java.time.LocalDate
import java.util.*

class ConsumptionWeb(
    val id: UUID,
    val fuelType: String,
    val pricePerLitter: Double,
    val volume: Double,
    val date: String,
    val driverId: Long
) {
    fun toConsumption(): Consumption =
        Consumption(
            id = id,
            fuelType = FuelType.valueOf(this.fuelType),
            pricePerLitter = this.pricePerLitter,
            volume = this.volume,
            date = LocalDate.parse(this.date),
            driver = Driver(id = this.driverId)
        )
}
