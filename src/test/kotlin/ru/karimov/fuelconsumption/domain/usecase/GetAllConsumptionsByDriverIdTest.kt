package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.adapter.repository.db.InMemoryConsumptionRepository
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.FuelType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Driver
import java.time.LocalDate
import java.util.*

class GetAllConsumptionsByDriverIdTest {
    @Test
    fun `get all consumptions by driver id success`() {
        val repo = InMemoryConsumptionRepository()
        val addUseCase = AddConsumption(repo)
        val getDriverIdUseCase = GetAllConsumptionsByDriverId(repo)
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(2L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(3L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(4L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(5L)))

        val res = getDriverIdUseCase.execute(3L)
        Assertions.assertEquals(1, res.size)
        Assertions.assertEquals(3L, res[0].driver.id)
    }

    @Test
    fun `get all consumptions by driver id fail`() {
        val repo = InMemoryConsumptionRepository()
        val addUseCase = AddConsumption(repo)
        val getDriverIdUseCase = GetAllConsumptionsByDriverId(repo)
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(2L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(3L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(4L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(5L)))

        val res = getDriverIdUseCase.execute(12345L)
        Assertions.assertEquals(0, res.size)
    }
}
