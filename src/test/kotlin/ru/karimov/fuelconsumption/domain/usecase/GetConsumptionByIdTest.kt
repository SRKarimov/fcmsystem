package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.adapter.repository.db.InMemoryConsumptionRepository
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.domain.usecase.exception.ConsumptionNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class GetConsumptionByIdTest {
    @Test
    fun `get consumption by id success`() {
        val repo = InMemoryConsumptionRepository()
        val getByIdUseCase = GetConsumptionById(repo)
        val addUseCase = AddConsumption(repo)
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab1"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12345L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab2"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12346L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab3"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12347L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab4"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12348L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab5"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12349L))

        val res = getByIdUseCase.execute(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab3"))
        Assertions.assertNotNull(res)
        Assertions.assertEquals(12347L, res.driverId)
    }

    @Test
    fun `get consumption by id fail`() {
        val repo = InMemoryConsumptionRepository()
        val getUseCase = GetConsumptionById(repo)
        val addUseCase = AddConsumption(repo)
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab1"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12345L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab2"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12346L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab3"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12347L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab4"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12348L))
        addUseCase.execute(Consumption(UUID.fromString("ed18a710-6f3c-404e-bba6-fc4276f32ab5"), FuelType.RON92, 1.45, 11.0, LocalDate.now(), 12349L))

        val ex = Assertions.assertThrows(ConsumptionNotFoundException::class.java) { getUseCase.execute(UUID.randomUUID()) }
        Assertions.assertEquals("Consumption not found", ex.message)
    }
}
