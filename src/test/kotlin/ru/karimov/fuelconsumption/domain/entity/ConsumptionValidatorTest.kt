package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*

//consumption.driverId <= 0 -> ConsumptionValidationException("DriverId should be greater than zero")

class ConsumptionValidatorTest {
    @Test
    fun `should be not null`() {
        val res = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L))
        Assertions.assertNotNull(res)
    }

    @Test
    fun `price should be greater than zero`() {
        val ex = Assertions.assertThrows(IllegalArgumentException::class.java) { Consumption(UUID.randomUUID(), FuelType.Diesel, 0.0, 11.0, LocalDate.now(), Driver(1L)) }
        Assertions.assertEquals("Price per litter should be greater than zero", ex.message)
    }

    @Test
    fun `volume should be greater than zero`() {
        val ex = Assertions.assertThrows(IllegalArgumentException::class.java) { Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 0.0, LocalDate.now(), Driver(1L)) }
        Assertions.assertEquals("Volume should be greater than zero", ex.message)
    }

    @Test
    fun `driverId should be greater than zero`() {
        val ex = Assertions.assertThrows(IllegalArgumentException::class.java) { Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(0L)) }
        Assertions.assertEquals("Driver id should be greater than zero", ex.message)
    }
}
