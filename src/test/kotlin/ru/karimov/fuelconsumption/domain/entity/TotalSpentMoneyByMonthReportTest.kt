package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class TotalSpentMoneyByMonthReportTest {
    @Test
    fun `should generate report for Dec and Jun`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
    }

    @Test
    fun `should generate report for Dec and sum = 31_90`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
        Assertions.assertEquals(31.90, report["DECEMBER"])
    }

    @Test
    fun `should generate report for Jun and sum = 15_95`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
        Assertions.assertEquals(15.95, report["JUNE"])
    }

    @Test
    fun `should generate report for August and sum = 63_80`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2016, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2017, 8, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2018, 8, 21), Driver(12347L))
        val consumptionFour = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12348L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree, consumptionFour)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertFalse(report.containsKey("DECEMBER"))
        Assertions.assertFalse(report.containsKey("JUNE"))
        Assertions.assertEquals(63.80, report["AUGUST"])
    }

    @Test
    fun `should generate report for Dec and Jun and driver 12345`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12345L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate(Driver(12345L))
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertTrue(report.containsKey("JUNE"))
    }

    @Test
    fun `should generate report for Dec and driver 12345 and sum = 31_90`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12345L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate(Driver(12345L))
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("DECEMBER"))
        Assertions.assertFalse(report.containsKey("JUNE"))
        Assertions.assertEquals(31.90, report["DECEMBER"])
    }

    @Test
    fun `should generate report for Jun and driver 12345 and sum = 15_95`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12346L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate(Driver(12345L))
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("JUNE"))
        Assertions.assertEquals(15.95, report["JUNE"])
    }

    @Test
    fun `should generate report for August and driver 12345 and sum = 63_80`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2016, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2017, 8, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2018, 8, 21), Driver(12345L))
        val consumptionFour = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12345L))
        val list =  listOf(consumptionOne, consumptionTwo, consumptionThree, consumptionFour)

        val report = TotalSpentMoneyByMonthReport(list)
            .generate()
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertFalse(report.containsKey("DECEMBER"))
        Assertions.assertFalse(report.containsKey("JUNE"))
        Assertions.assertEquals(63.80, report["AUGUST"])
    }
}
