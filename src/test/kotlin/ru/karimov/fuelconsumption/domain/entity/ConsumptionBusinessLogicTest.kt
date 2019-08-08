package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

/*
* total spent amount of money grouped by month
* list of fuel consumption records for specified month (each row should contain: fuel type, volume, date, price, total price, driver ID)
* statistics for each month, list fuel consumption records grouped by fuel type (each row should contain: fuel type, volume, average price, total price)
* Every request can be made either for all drivers or for the specific driver (identified by id)
*/

class ConsumptionBusinessLogicTest {
    @Test
    fun `should calculate total price`() {
        val consumption = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), 1L)
        Assertions.assertEquals(15.95, TotalPrice(consumption)())
    }
}
