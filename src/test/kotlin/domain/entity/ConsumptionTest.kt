package domain.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.time.LocalDate

class ConsumptionTest {
    @Test
    fun `should create consumption`() {
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
        assertEquals(15.95, consumption.TotalPrice())
    }

    @Test
    fun `limit price per litter - should throw PriceFailureException`() {
        val consumption = Consumption(1L, FuelType.Diesel, -1.45, 11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertThrows(PriceFailureException::class.java) { consumption.TotalPrice() }
    }

    @Test
    fun `limit volume - should throw VolumeFailureException`() {
        val consumption = Consumption(1L, FuelType.Diesel, 1.45, -11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertThrows(VolumeFailureException::class.java) { consumption.TotalPrice() }
    }

    @Test
    fun `should return string`() {
        val consumption = Consumption(1L, FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L)
        assertNotNull(consumption)
        assertEquals("User{id='1', fuelType='Diesel', pricePerLitter='1.45', volume='11.0', date='2019-08-05', driverId='1', totalPrice='15.95'}", consumption.toString())
    }
}
