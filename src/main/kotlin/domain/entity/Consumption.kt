package domain.entity

import domain.exception.PriceFailureException
import domain.exception.VolumeFailureException
import java.time.LocalDate

data class Consumption (
        val id: Long,
        val fuelType: FuelType, //fuel type(Ex. 95, 98 or D)
        val pricePerLitter: Double, //price per litter in EUR (Ex. 10.10
        val volume: Double, //volume in litters (Ex. 12.5)
        val date: LocalDate, //date (Ex. 01.21.2018)
        val driverId: Long //driver ID (Ex. 12345)
){
    fun TotalPrice(): Double {
        return when {
            (this.volume < 0) -> throw VolumeFailureException("Volume should be greater than zero")
            (this.pricePerLitter < 0) -> throw PriceFailureException("Price should be greater than zero")
            else -> pricePerLitter * volume
        }
    }

    override fun  toString(): String = "User{" +
            "id='" + id + '\'' +
            ", fuelType='" + fuelType + '\'' +
            ", pricePerLitter='" + pricePerLitter + '\'' +
            ", volume='" + volume + '\'' +
            ", date='" + date.toString() + '\'' +
            ", driverId='" + driverId + '\'' +
            ", totalPrice='" + TotalPrice() + '\'' +
            '}'
}
