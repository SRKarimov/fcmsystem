package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class ListOfPurchasesForMonthTest {
    @BeforeEach
    fun beforeEach() {
        val list = ListOfConsumption()
        list.clearConsumptions()
    }

    @Test
    fun `should get list of purchases for August`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = ListOfPurchasesForMonthReport().generate("August")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(3, report.size)
    }

    @Test
    fun `should get two records of purchases for August`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 9, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = ListOfPurchasesForMonthReport().generate("August")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
    }

    @Test
    fun `should get one record of purchases for September`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 9, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = ListOfPurchasesForMonthReport().generate("September")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
    }

    @Test
    fun `should not get any records of purchases for December`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 9, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 10, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = ListOfPurchasesForMonthReport().generate("December")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(0, report.size)
    }
}
