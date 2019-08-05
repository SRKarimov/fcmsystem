package domain.entity

import domain.exception.ConsumptionValidationException
import java.time.LocalDate

class ConsumptionValidator {
    fun ValidateCreateConsumption(consumption: Consumption) {
        when {
            consumption == null -> ConsumptionValidationException("Consumption should not be null")
            consumption.id <= 0 -> ConsumptionValidationException("Id should be greater than zero")
            consumption.fuelType == null -> ConsumptionValidationException("Fuel type should not be null")
            consumption.pricePerLitter <= 0 -> ConsumptionValidationException("Price should be greater than zero")
            consumption.volume <= 0 -> ConsumptionValidationException("Volume should be greater than zero")
            consumption.date == null -> ConsumptionValidationException("Date should not be null")
            consumption.driverId <= 0 -> ConsumptionValidationException("DriverId should be greater than zero")
        }
    }
}
