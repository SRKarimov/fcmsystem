package ru.karimov.fuelconsumption.domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class StatisticsForMonthGroupedByFuelTypeReportTest {
    @Test
    fun `should generate report for Dec`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.RON92, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate("December")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertTrue(report.containsKey("RON92"))
        Assertions.assertFalse(report.containsKey("RON98"))
    }

    @Test
    fun `should generate report for Dec and avg = 1_45`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate("December")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertEquals(1.45, report["Diesel"]?.averagePrice)
    }

    @Test
    fun `should generate report for Jun and volume = 11_00`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate("June")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertEquals(11.00, report["Diesel"]?.volume)
    }

    @Test
    fun `should generate report for August and total price = 63_80`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2016, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2017, 8, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2018, 8, 21), Driver(12347L))
        val consumptionFour = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12348L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree, consumptionFour)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate("August")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertFalse(report.containsKey("RON92"))
        Assertions.assertEquals(63.80, report["Diesel"]?.totalPrice)
    }

    @Test
    fun `should generate report for Dec and driver 12345`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.RON92, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12345L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate(driverId = 12345L, month = "December")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(2, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertTrue(report.containsKey("RON92"))
    }

    @Test
    fun `should generate report for Dec and driver 12345 and total price = 31_90`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12346L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12345L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate(12345L, month = "December")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertFalse(report.containsKey("RON92"))
        Assertions.assertEquals(31.90, report["Diesel"]?.totalPrice)
    }

    @Test
    fun `should generate report for Jun and driver 12345 and sum = 15_95`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 1), Driver(12346L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 6, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 12, 21), Driver(12347L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate(12345L, month = "June")
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertEquals(15.95, report["Diesel"]?.totalPrice)
    }

    @Test
    fun `should generate report for August and driver 12345 and sum = 63_80`() {
        val consumptionOne = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2016, 8, 1), Driver(12345L))
        val consumptionTwo = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2017, 8, 11), Driver(12345L))
        val consumptionThree = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2018, 8, 21), Driver(12345L))
        val consumptionFour = Consumption(UUID.randomUUID(), FuelType.Diesel, 1.45, 11.0, LocalDate.of(2019, 8, 21), Driver(12345L))
        val list = listOf(consumptionOne, consumptionTwo, consumptionThree, consumptionFour)

        val report = StatisticsForMonthGroupedByFuelTypeReport(list)
            .generate(month = "August", driverId = 12345L)
        Assertions.assertNotNull(report)
        Assertions.assertEquals(1, report.size)
        Assertions.assertTrue(report.containsKey("Diesel"))
        Assertions.assertFalse(report.containsKey("RON95"))
        Assertions.assertEquals(63.80, report["Diesel"]?.totalPrice)
    }
}