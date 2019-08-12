package ru.karimov.fuelconsumption.domain.entity

import java.time.LocalDate
import java.util.*

class Consumption(
    val id: UUID = UUID.randomUUID(),
    val fuelType: FuelType = FuelType.Diesel,
    val pricePerLitter: Double,
    val volume: Double,
    val date: LocalDate?,
    val driver: Driver
){
    init {
        require (volume > 0) { "Volume should be greater than zero" }
        require ( pricePerLitter > 0) { "Price per litter should be greater than zero" }
    }

    override fun toString(): String = "Consumption{" +
            "id='" + id + '\'' +
            ", fuelType='" + fuelType + '\'' +
            ", pricePerLitter='" + pricePerLitter + '\'' +
            ", volume='" + volume + '\'' +
            ", date='" + date.toString() + '\'' +
            ", driverId='" + driver.id + '\'' +
            '}'
}
