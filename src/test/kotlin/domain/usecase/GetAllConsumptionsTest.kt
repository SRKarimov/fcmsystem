package domain.usecase

import adapter.repository.db.InMemoryConsumptionRepository
import domain.entity.Consumption
import domain.entity.FuelType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class GetAllConsumptionsTest {
    @Test
    fun `get all consumptions success`() {
        val repo = InMemoryConsumptionRepository()
        val addUseCase = AddConsumption(repo)
        val getAllUseCase = GetAllConsumptions(repo)
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 2L))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 3L))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 4L))
        addUseCase.execute(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 5L))

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
