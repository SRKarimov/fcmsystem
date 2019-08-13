package ru.karimov.fuelconsumption

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import ru.karimov.fuelconsumption.infrastructure.controller.model.ConsumptionDto
import ru.karimov.fuelconsumption.infrastructure.controller.model.PurchaseDto
import ru.karimov.fuelconsumption.infrastructure.controller.model.StatisticsDto
import java.time.LocalDate
import java.util.*

@RunWith(SpringRunner::class)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @BeforeAll
    fun beforeAll() {
        testRestTemplate.postForEntity(
            "/consumption/save",
            ConsumptionDto(id = UUID.randomUUID(), fuelType = "RON95", volume = 11.0, driverId = 12345L, pricePerLitter = 1.45, date = LocalDate.of(2019, 12, 13).toString()),
            ConsumptionDto::class.java
        )
        testRestTemplate.postForEntity(
            "/consumption/save",
            ConsumptionDto(id = UUID.randomUUID(), fuelType = "RON91", volume = 11.0, driverId = 12345L, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13).toString()),
            ConsumptionDto::class.java
        )
        testRestTemplate.postForEntity(
            "/consumption/save",
            ConsumptionDto(id = UUID.randomUUID(), fuelType = "RON92", volume = 11.0, driverId = 12346L, pricePerLitter = 1.45, date = LocalDate.of(2019, 12, 13).toString()),
            ConsumptionDto::class.java
        )
        testRestTemplate.postForEntity(
            "/consumption/save",
            ConsumptionDto(id = UUID.randomUUID(), fuelType = "RON98", volume = 11.0, driverId = 12346L, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13).toString()),
            ConsumptionDto::class.java
        )
        testRestTemplate.postForEntity(
            "/consumption/save",
            ConsumptionDto(id = UUID.randomUUID(), fuelType = "Diesel", volume = 11.0, driverId = 12347L, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13).toString()),
            ConsumptionDto::class.java
        )
    }

    @Test
    fun GetPurchaseListTest() {
        data class PurchaseResponse(val purchases: List<PurchaseDto>)
        val result = testRestTemplate.getForEntity("/purchase/December", PurchaseResponse::class.java)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(2, result.body.purchases.size)
    }
    @Test
    fun GetPurchaseListForDriverTest() {
        data class PurchaseResponse(val purchases: List<PurchaseDto>)
        val result = testRestTemplate.getForEntity("/purchase/December/12345", PurchaseResponse::class.java)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(1, result.body.purchases.size)
    }

    @Test
    fun GetStatisticsTest() {
        data class StatisticsResponse(val statistics: Map<String, StatisticsDto>)
        val result = testRestTemplate.getForEntity("/statistics/December", StatisticsResponse::class.java)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(2, result.body.statistics.size)
    }

    @Test
    fun GetStatisticsForDriverTest() {
        data class StatisticsResponse(val statistics: Map<String, StatisticsDto>)
        val result = testRestTemplate.getForEntity("/statistics/December/12346", StatisticsResponse::class.java)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(2, result.body.statistics.size)
    }
//
//    @Test
//    fun GetTotalSpentMoneyTest() {
//        val result = testRestTemplate.getForEntity("/hello/data", Hello::class.java)
//        Assertions.assertNotNull(result)
//        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
//        Assertions.assertEquals(result.body, Hello("Hello data!"))
//    }
//
//    @Test
//    fun GetTotalSpentMoneyForDriverTest() {
//        val result = testRestTemplate.getForEntity("/hello/data", Hello::class.java)
//        Assertions.assertNotNull(result)
//        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
//        Assertions.assertEquals(result.body, Hello("Hello data!"))
//    }
}
