package domain.entity

import domain.exception.PriceFailureException
import domain.exception.VolumeFailureException
import java.time.LocalDate

class Consumption (
        id: Long,
        fuelType: FuelType?, //fuel type(Ex. 95, 98 or D)
        pricePerLitter: Double, //price per litter in EUR (Ex. 10.10
        volume: Double, //volume in litters (Ex. 12.5)
        date: LocalDate?, //date (Ex. 01.21.2018)
        driverId: Long //driver ID (Ex. 12345)
){
    val id = id
    val fuelType = fuelType ?: FuelType.RON92
    val pricePerLitter = when {
        pricePerLitter <= 0 -> throw PriceFailureException("Price should be greater than zero")
        else -> pricePerLitter
    }

    val volume = when {
        volume <= 0 -> throw VolumeFailureException("Volume should be greater than zero")
        else -> volume
    }
    val date = date ?: LocalDate.now()
    val driverId = driverId
    val totalPrice = when {
            (this.volume < 0) -> throw VolumeFailureException("Volume should be greater than zero")
            (this.pricePerLitter < 0) -> throw PriceFailureException("Price should be greater than zero")
            else -> pricePerLitter * volume
        }

    override fun toString(): String = "User{" +
            "id='" + id + '\'' +
            ", fuelType='" + fuelType + '\'' +
            ", pricePerLitter='" + pricePerLitter + '\'' +
            ", volume='" + volume + '\'' +
            ", date='" + date.toString() + '\'' +
            ", driverId='" + driverId + '\'' +
            ", totalPrice='" + totalPrice + '\'' +
            '}'
}
