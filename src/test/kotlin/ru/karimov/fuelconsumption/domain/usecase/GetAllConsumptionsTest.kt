package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.adapter.repository.db.InMemoryConsumptionRepository
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.FuelType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Driver
import java.time.LocalDate
import java.util.*

class GetAllConsumptionsTest {
    @Test
    fun `get all consumptions success`() {
        val repo = InMemoryConsumptionRepository()
        val addUseCase = SaveConsumption(repo)
        val getAllUseCase = GetAllConsumptions(repo)
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(1L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(2L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(3L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(4L)))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(5L)))

        val res = getAllUseCase.execute()
        Assertions.assertEquals(5, res.size)
    }

    @Test
    fun `get all consumptions fail`() {
        val repo = InMemoryConsumptionRepository()
        val getAllUseCase = GetAllConsumptions(repo)
        val res = getAllUseCase.execute()
        Assertions.assertEquals(0, res.size)
    }
}
