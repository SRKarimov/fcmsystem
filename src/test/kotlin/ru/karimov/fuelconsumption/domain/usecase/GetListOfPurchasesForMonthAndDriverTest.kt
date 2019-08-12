package ru.karimov.fuelconsumption.domain.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import java.time.LocalDate
import java.util.*

class GetListOfPurchasesForMonthAndDriverTest {
    @Test
    fun `should get list of purchases for Dec and driver 12345 success`() {
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

        val result = GetListOfPurchasesForMonthAndDriver(inMemoryConsumption, inMemoryDriver).execute(driverId = 12345L, month = "December")
        Assertions.assertEquals(1, result.size)
    }

    @Test
    fun `should get list of purchases for Dec fail`() {
        val inMemoryConsumption = InMemoryConsumption()
        val inMemoryDriver = InMemoryDriver()
        val driver = inMemoryDriver.save(Driver(id = 12345L))

        inMemoryConsumption.save(
            Consumption(
                id = UUID.randomUUID(),
                date = LocalDate.of(2019, 8, 1),
                volume = 11.0,
                fuelType = FuelType.RON91,
                pricePerLitter = 1.1,
                driver = driver
            )
        )

        val result = GetListOfPurchasesForMonthAndDriver(inMemoryConsumption, inMemoryDriver).execute(driverId = 12345L, month = "December")
        Assertions.assertEquals(0, result.size)
    }
}
