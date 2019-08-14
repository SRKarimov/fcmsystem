package ru.karimov.fuelconsumption

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.entity.FuelType
import ru.karimov.fuelconsumption.infrastructure.controller.model.PurchaseDto
import ru.karimov.fuelconsumption.infrastructure.controller.model.StatisticsDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests(
    @Autowired private val testRestTemplate: TestRestTemplate
    ) {
    val consumptionRepository = InMemoryConsumption()
    val driverRepository = InMemoryDriver()

    @BeforeEach
    fun before() {
        consumptionRepository.deleteAll()
        driverRepository.deleteAll()

        val driver12345 = driverRepository.save(Driver(id = 12345L))
        val driver12346 = driverRepository.save(Driver(id = 12346L))
        val driver12347 = driverRepository.save(Driver(id = 12347L))

        consumptionRepository.save(Consumption(id = UUID.randomUUID(), fuelType = FuelType.RON95, volume = 11.0, driver = driver12345, pricePerLitter = 1.45, date = LocalDate.of(2019, 12, 13)))
        consumptionRepository.save(Consumption(id = UUID.randomUUID(), fuelType = FuelType.RON91, volume = 11.0, driver = driver12345, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13)))
        consumptionRepository.save(Consumption(id = UUID.randomUUID(), fuelType = FuelType.RON92, volume = 11.0, driver = driver12346, pricePerLitter = 1.45, date = LocalDate.of(2019, 12, 13)))
        consumptionRepository.save(Consumption(id = UUID.randomUUID(), fuelType = FuelType.RON98, volume = 11.0, driver = driver12346, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13)))
        consumptionRepository.save(Consumption(id = UUID.randomUUID(), fuelType = FuelType.Diesel, volume = 11.0, driver = driver12347, pricePerLitter = 1.45, date = LocalDate.of(2019, 8, 13)))
    }

    @Test
    @DirtiesContext
    fun getHelloTest() {
        val result = testRestTemplate.getForEntity("/", String::class.java)
        Assertions.assertNotNull(result)
        Assertions.assertEquals("Hello, user!", result.body.toString())
    }

    @Test
    @DirtiesContext
    fun getPurchaseListTest() {
        val result = testRestTemplate.getForEntity<List<PurchaseDto>>("/purchase/december")
        val list = result.body as List<PurchaseDto>
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertNotNull(list)
        Assertions.assertEquals(2, list.size)
    }

    @Test
    @DirtiesContext
    fun getPurchaseListForDriverTest() {
        val result = testRestTemplate.getForEntity<List<PurchaseDto>>("/purchase/december/12345")
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(1, result.body.size)
    }

    @Test
    fun getStatisticsTest() {
        val result = testRestTemplate.getForEntity<Map<String, StatisticsDto>>("/statistics/December")
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(2, result.body.size)
    }

    @Test
    fun getStatisticsForDriverTest() {
        val result = testRestTemplate.getForEntity<Map<String, StatisticsDto>>("/statistics/december/12346")
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(1, result.body.size)
    }
}