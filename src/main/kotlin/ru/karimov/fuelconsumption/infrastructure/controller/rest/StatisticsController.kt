package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.infrastructure.controller.model.StatisticsDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.StatisticsForMonthGroupedByFuelType
import ru.karimov.fuelconsumption.usecase.StatisticsForMonthGroupedByFuelTypeForDriver

@RestController
@RequestMapping("/statistics")
class StatisticsController {
    @GetMapping("/{month}")
    @ResponseBody
    fun create(@PathVariable("month") month: String): Map<String, StatisticsDto> {
        val repository = InMemoryConsumption()

        val result = StatisticsForMonthGroupedByFuelType(repository).execute(month = month)

        var statisticsResponseDto = mutableMapOf<String, StatisticsDto>()
        for ((key, value) in result)
            statisticsResponseDto[key] = StatisticsDto(
                volume = value.volume,
                averagePrice = value.averagePrice,
                totalPrice = value.totalPrice
            )
        return statisticsResponseDto
    }

    @GetMapping("/{month}/{driverId}")
    @ResponseBody
    fun createBulk(
        @PathVariable("driverId") driverId: Long,
        @PathVariable("month") month: String
    ): Map<String, StatisticsDto> {
        val repository = InMemoryConsumption()
        val driverRepository = InMemoryDriver()

        val result = StatisticsForMonthGroupedByFuelTypeForDriver(
            driverRepository = driverRepository,
            consumptionRepository = repository
        ).execute(driverId = driverId, month = month)

        var statisticsResponseDto = mutableMapOf<String, StatisticsDto>()
        for ((key, value) in result)
            statisticsResponseDto[key] = StatisticsDto(
                volume = value.volume,
                averagePrice = value.averagePrice,
                totalPrice = value.totalPrice
            )

        return statisticsResponseDto
    }
}
