package domain.entity

import domain.exception.DriverIdFailureException
import domain.exception.PriceFailureException
import domain.exception.VolumeFailureException
import java.time.LocalDate
import java.util.*

class Consumption(
    val id: UUID = UUID.randomUUID(),
    val fuelType: FuelType?,
    val pricePerLitter: Double,
    val volume: Double,
    val date: LocalDate?,
    val driverId: Long
){
    init {
        if (fuelType == null) FuelType.RON92
        if (volume <= 0) throw VolumeFailureException("Volume should be greater than zero")
        if (pricePerLitter <= 0) throw PriceFailureException("Price per litter should be greater than zero")
        if (driverId <= 0) throw DriverIdFailureException("Driver id should be greater than zero")
    }

    override fun toString(): String = "Consumption{" +
            "id='" + id + '\'' +
            ", fuelType='" + fuelType + '\'' +
            ", pricePerLitter='" + pricePerLitter + '\'' +
            ", volume='" + volume + '\'' +
            ", date='" + date.toString() + '\'' +
            ", driverId='" + driverId + '\'' +
            '}'
}
