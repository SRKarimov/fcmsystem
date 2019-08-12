package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*

class ConsumptionTest {
    @Test
    fun `should create instance of consumption`() {
        val consumption = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L))
        assertNotNull(consumption)
        assertEquals(FuelType.Diesel, consumption.fuelType)
        assertEquals(1.45, consumption.pricePerLitter)
        assertEquals(11.0, consumption.volume)
        assertEquals(LocalDate.now(), consumption.date)
        assertEquals(1L, consumption.driver.id)
    }

    @Test
    fun `should calculate total price`() {
        val consumption = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L))
        assertNotNull(consumption)
        assertEquals(15.95, TotalPrice(consumption)())
    }

    @Test
    fun `limit price per litter - should throw PriceFailureException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) { Consumption(UUID.randomUUID(), FuelType.Diesel, -1.45, 11.0, LocalDate.now(), Driver(1L)) }
        assertEquals("Price per litter should be greater than zero", exception.message)
    }

    @Test
    fun `limit volume - should throw VolumeFailureException`() {
        val exception = assertThrows(IllegalArgumentException::class.java) { Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, -11.0, LocalDate.now(), Driver(1L)) }
        assertEquals("Volume should be greater than zero", exception.message)
    }

    @Test
    fun `should return string`() {
        val consumption = Consumption(UUID.fromString("f6484908-476f-44e1-97e1-45ebdca6e23d"), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L))
        assertNotNull(consumption)
        assertEquals("Consumption{id='f6484908-476f-44e1-97e1-45ebdca6e23d', fuelType='Diesel', pricePerLitter='1.45', volume='11.0', date='${LocalDate.now().toString()}', driverId='1'}", consumption.toString())
    }
}
