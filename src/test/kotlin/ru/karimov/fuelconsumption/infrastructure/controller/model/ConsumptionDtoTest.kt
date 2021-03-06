package ru.karimov.fuelconsumption.infrastructure.controller.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class ConsumptionDtoTest {
    @Test
    fun `should return consumption from web`() {
        val consumptionWeb = ConsumptionDto(UUID.fromString("645f04ff-d987-4439-993c-b972aac35ff6"), "RON98", 1.45, 11.0, LocalDate.now().toString(), 12345L)
        val consumption = consumptionWeb.toConsumption()
        Assertions.assertEquals("Consumption{id='645f04ff-d987-4439-993c-b972aac35ff6', fuelType='RON98', pricePerLitter='1.45', volume='11.0', date='${LocalDate.now().toString()}', driverId='12345'}", consumption.toString())
    }
}
