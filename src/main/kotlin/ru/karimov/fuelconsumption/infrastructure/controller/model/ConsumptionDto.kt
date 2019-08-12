package ru.karimov.fuelconsumption.infrastructure.controller.model

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import java.time.LocalDate
import java.util.*

class ConsumptionDto(
    var id: UUID,
    var fuelType: String,
    var pricePerLitter: Double,
    var volume: Double,
    var date: String,
    var driverId: Long
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

    fun fromConsumption(consumption: Consumption): ConsumptionDto {
        this.id = consumption.id
        this.fuelType = consumption.fuelType.toString()
        this.pricePerLitter = consumption.pricePerLitter
        this.volume = consumption.volume
        this.date = consumption.date.toString()
        this.driverId = consumption.driver.id
        return this
    }
}
