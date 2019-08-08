package ru.karimov.fuelconsumption.domain.entity

class TotalPrice(val consumption: Consumption) {
    operator fun invoke(): Double {
        return consumption.volume * consumption.pricePerLitter
    }
}
