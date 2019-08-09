package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class ListOfConsumptionTest {
    @BeforeEach
    fun beforeEach() {
        val list = ListOfConsumption()
        list.clearConsumptions()
    }

    @Test
    fun `should be instance of list of consumption`() {
        val list = ListOfConsumption()
        Assertions.assertNotNull(list)
    }

    @Test
    fun `should get consumption`() {
        val list = ListOfConsumption(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12345L)))
        val consumption = list.getConsumptions()
        Assertions.assertNotNull(list)
        Assertions.assertEquals(1, consumption.size)
    }

    @Test
    fun `should save consumption`() {
        val consumption = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12345L))
        val list = ListOfConsumption(consumption)
        Assertions.assertNotNull(list)
        Assertions.assertEquals(1, list.getConsumptions().size)
    }

    @Test
    fun `should save two consumptions`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12346L))
        val list = ListOfConsumption(listOf(consumptionOne, consumptionTwo))
        Assertions.assertNotNull(list)
        Assertions.assertEquals(2, list.getConsumptions().size)
        Assertions.assertEquals(12346L, list.getConsumptions()[1].driver.id)
    }

    @Test
    fun `should add consumption`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12346L))
        val list = ListOfConsumption(listOf(consumptionOne, consumptionTwo))
        list.save(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12347L)))
        Assertions.assertNotNull(list)
        Assertions.assertEquals(3, list.getConsumptions().size)
        Assertions.assertEquals(12347L, list.getConsumptions()[2].driver.id)
    }

    @Test
    fun `should add two consumptions`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12346L))
        val list = ListOfConsumption(listOf(consumptionOne, consumptionTwo))
        list.save(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12347L)))
        list.save(Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.now(), Driver(12348L)))
        Assertions.assertNotNull(list)
        Assertions.assertEquals(4, list.getConsumptions().size)
        Assertions.assertEquals(12347L, list.getConsumptions()[2].driver.id)
    }
}
