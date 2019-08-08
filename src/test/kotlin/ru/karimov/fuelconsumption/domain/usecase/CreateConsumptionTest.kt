package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.adapter.repository.db.InMemoryConsumptionRepository
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.domain.exception.DriverIdFailureException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class CreateConsumptionTest{
    @Test
    fun `should create consumption success`() {
        val repo = InMemoryConsumptionRepository()
        val useCase = AddConsumption(repo)
        val consumption = useCase.execute(Consumption(UUID.fromString("7b3e5b24-6f03-4cad-af3c-c1c76bfb1cb9"), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 12345L))

        Assertions.assertNotNull(consumption)
        Assertions.assertEquals("Consumption{id='7b3e5b24-6f03-4cad-af3c-c1c76bfb1cb9', fuelType='Diesel', pricePerLitter='1.45', volume='11.0', date='${LocalDate.now().toString()}', driverId='12345'}", consumption.toString())
    }

    @Test
    fun `should create consumption fail`() {
        val repo = InMemoryConsumptionRepository()
        val useCase = AddConsumption(repo)
        val ex = Assertions.assertThrows(DriverIdFailureException::class.java) { useCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 0L)) }
        Assertions.assertEquals("Driver id should be greater than zero", ex.message)
    }
}
