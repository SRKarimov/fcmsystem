package ru.karimov.fuelconsumption.usecases

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetListOfPurchasesForMonthAndDriver
import java.time.LocalDate
import java.util.*

class GetListOfPurchasesForMonthAndDriverTest {
    @Test
    fun `should get list of purchases for Dec and driver 12345 success`() {
        val inMemoryConsumption = InMemoryConsumption()
        inMemoryConsumption.deleteAll()
        val inMemoryDriver = InMemoryDriver()
        inMemoryDriver.deleteAll()

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

        val result = GetListOfPurchasesForMonthAndDriver(
            inMemoryConsumption,
            inMemoryDriver
        ).execute(driverId = 12345L, month = "December")
        Assertions.assertEquals(1, result.size)
    }

    @Test
    fun `should get list of purchases for Sep fail`() {
        val inMemoryConsumption = InMemoryConsumption()
        inMemoryConsumption.deleteAll()
        val inMemoryDriver = InMemoryDriver()
        inMemoryDriver.deleteAll()
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

        val result = GetListOfPurchasesForMonthAndDriver(
            inMemoryConsumption,
            inMemoryDriver
        ).execute(driverId = 12345L, month = "september")
        Assertions.assertEquals(0, result.size)
    }
}
