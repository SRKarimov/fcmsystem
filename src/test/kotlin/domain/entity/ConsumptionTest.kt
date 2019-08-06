package domain.entity

import domain.exception.PriceFailureException
import domain.exception.VolumeFailureException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ConsumptionTest {
    @Test
    fun `should create instance of consumption`() {
        val consumption = Consumption(1L, FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertEquals(1L, consumption.id)
        assertEquals(FuelType.Diesel, consumption.fuelType)
        assertEquals(1.45, consumption.pricePerLitter)
        assertEquals(11.0, consumption.volume)
        assertEquals(LocalDate.now(), consumption.date)
        assertEquals(1L, consumption.driverId)
    }

    @Test
    fun `should calculate total price`() {
        val consumption = Consumption(1L, FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertEquals(15.95, consumption.totalPrice)
    }

    @Test
    fun `limit price per litter - should throw PriceFailureException`() {
        val exception = assertThrows(PriceFailureException::class.java) { Consumption(1L, FuelType.Diesel, -1.45, 11.0, LocalDate.now(), 1L) }
        assertEquals("Price should be greater than zero", exception.message)
    }

    @Test
    fun `limit volume - should throw VolumeFailureException`() {
        val exception = assertThrows(VolumeFailureException::class.java) { Consumption(1L, FuelType.Diesel, 1.45, -11.0, LocalDate.now(), 1L) }
        assertEquals("Volume should be greater than zero", exception.message)    }

    @Test
    fun `should return string`() {
        val consumption = Consumption(1L, FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertEquals("User{id='1', fuelType='Diesel', pricePerLitter='1.45', volume='11.0', date='${LocalDate.now().toString()}', driverId='1', totalPrice='15.95'}", consumption.toString())
    }
}
