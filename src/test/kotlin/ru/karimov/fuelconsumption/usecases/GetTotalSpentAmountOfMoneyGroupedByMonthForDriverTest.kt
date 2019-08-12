package ru.karimov.fuelconsumption.usecases

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonthForDriver
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*

class GetTotalSpentAmountOfMoneyGroupedByMonthForDriverTest {
    @Test
    fun `should get total money for driver success`() {
        val inMemoryConsumption = InMemoryConsumption()
        val inMemoryDriver = InMemoryDriver()
        val driver = inMemoryDriver.save(Driver(id = 12345L))

        inMemoryConsumption.save(
            Consumption(
                id = UUID.randomUUID(),
                date = LocalDate.of(2019, 12, 1),
                volume = 11.0,
                fuelType = FuelType.RON91,
                pricePerLitter = 1.1,
                driver = driver
            )
        )

        val result = GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
            inMemoryConsumption,
            inMemoryDriver
        ).execute(driverId = 12345L)
        Assertions.assertEquals(1, result.size)
    }

    @Test
    fun `should get total money for driver fail`() {
        val inMemoryConsumption = InMemoryConsumption()
        val inMemoryDriver = InMemoryDriver()
        val driver = inMemoryDriver.save(Driver(id = 12345L))

        inMemoryConsumption.save(
            Consumption(
                id = UUID.randomUUID(),
                date = LocalDate.of(2019, 12, 1),
                volume = 11.0,
                fuelType = FuelType.RON91,
                pricePerLitter = 1.1,
                driver = driver
            )
        )

        val ex = Assertions.assertThrows(IllegalArgumentException::class.java) { GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
            inMemoryConsumption,
            inMemoryDriver
        ).execute(driverId = 1L) }
        Assertions.assertEquals("Consumption not found", ex.message)
    }
}
