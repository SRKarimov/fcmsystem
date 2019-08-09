package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class TotalSpentMoneyByMonthReportTest {
    @BeforeEach
    fun beforeEach() {
        val list = ListOfConsumption()
        list.clearConsumptions()
    }

    @Test
    fun `should generate report for Dec and Jun`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = TotalSpentMoneyByMonthReport().generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
    }

    @Test
    fun `should generate report for Dec and count should be 2`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = TotalSpentMoneyByMonthReport().generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
        Assertions.assertEquals(2, report["DECEMBER"]?.size)
    }

    @Test
    fun `should generate report for Jun and count should be 1`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree))

        val report = TotalSpentMoneyByMonthReport().generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
        Assertions.assertEquals(1, report["JUNE"]?.size)
    }

    @Test
    fun `should generate report for August`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2016, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2017, 8, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2018, 8, 21), Driver(12347L))
        val consumptionFour = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12348L))
        ListOfConsumption(listOf(consumptionOne, consumptionTwo, consumptionThree, consumptionFour))

        val report = TotalSpentMoneyByMonthReport().generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertFalse(report.containsKey("DECEMBER"))
        Assertions.assertFalse(report.containsKey("JUNE"))
        Assertions.assertEquals(4, report["AUGUST"]?.size)
    }
}
