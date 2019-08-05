package domain.entity

import java.time.LocalDate

class Consumption (
        val id: Long,
        val fuelType: FuelType, //fuel type(Ex. 95, 98 or D)
        val pricePerLitter: Double, //price per litter in EUR (Ex. 10.10
        val volume: Double, //volume in litters (Ex. 12.5)
        val date: LocalDate, //date (Ex. 01.21.2018)
        val driverId: Long //driver ID (Ex. 12345)
){
    fun TotalPrice(): Double {
        return when {
            (this.volume < 0) -> throw VolumeFailureException()
            (this.pricePerLitter < 0) -> throw PriceFailureException()
            else -> pricePerLitter * volume
        }
    }
}

class VolumeFailureException() : RuntimeException()
class PriceFailureException() : RuntimeException()
