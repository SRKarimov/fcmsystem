package ru.karimov.fuelconsumption.usecases

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonth
import java.time.LocalDate
import java.util.*

class GetTotalSpentAmountOfMoneyGroupedByMonthTest {
    @Test
    fun `should get total money success`() {
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

        val result = GetTotalSpentAmountOfMoneyGroupedByMonth(inMemoryConsumption).execute()
        Assertions.assertEquals(1, result.size)
    }
}
