package domain.entity

import domain.exception.ConsumptionValidationException
import domain.exception.PriceFailureException
import domain.exception.VolumeFailureException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

//consumption.driverId <= 0 -> ConsumptionValidationException("DriverId should be greater than zero")

class ConsumptionValidatorTest {
    @Test
    fun `should be not null`() {
        val res = Consumption(1L, FuelType.RON98, 1.11, 10.0, LocalDate.now(), 123L)
        Assertions.assertNotNull(res)
    }

    @Test
    fun `fuel type should not be null`() {
        try {
            Consumption(0L, null, 1.11, 10.0, LocalDate.now(), 123L)
        } catch (ex: ConsumptionValidationException) {
            Assertions.assertEquals("Id should be greater than zero", ex.message)
        }
    }

    @Test
    fun `price should be greater than zero`() {
        try {
            Consumption(0L, FuelType.RON98, 0.0, 10.0, LocalDate.now(), 123L)
        } catch (ex: PriceFailureException) {
            Assertions.assertEquals("Price should be greater than zero", ex.message)
        }
    }

    @Test
    fun `volume should be greater than zero`() {
        try {
            Consumption(0L, FuelType.RON98, 1.11, 0.0, LocalDate.now(), 123L)
        } catch (ex: VolumeFailureException) {
            Assertions.assertEquals("Volume should be greater than zero", ex.message)
        }
    }

    @Test
    fun `date should not be null`() {
        try {
            Consumption(0L, FuelType.RON98, 1.11, 10.0, null, 123L)
        } catch (ex: PriceFailureException) {
            Assertions.assertEquals("Date should not be null", ex.message)
        }
    }

    @Test
    fun `driverId should be greater than zero`() {
        try {
            Consumption(0L, FuelType.RON98, 1.11, 10.0, LocalDate.now(), 0L)
        } catch (ex: ConsumptionValidationException) {
            Assertions.assertEquals("DriverId should be greater than zero", ex.message)
        }
    }

    @Test
    fun `id should be greater than zero`() {
        try {
            Consumption(0L, FuelType.RON98, 1.11, 10.0, LocalDate.now(), 123L)
        } catch (ex: ConsumptionValidationException) {
            Assertions.assertEquals("Id should be greater than zero", ex.message)
        }
    }
}
