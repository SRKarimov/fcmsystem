package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonth
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonthForDriver

@RestController
@RequestMapping("/total_spent_money")
class TotalSpentMoneyController {
    @GetMapping("/")
    fun create(): Map<String, Double> {
        val repository = InMemoryConsumption()

        return GetTotalSpentAmountOfMoneyGroupedByMonth(repository).execute()
    }

    @GetMapping("/{driverId}")
    fun createBulk(
        @PathVariable("driverId") driverId: Long,
        @PathVariable("month") month: String
    ): Map<String, Double> {
        val repository = InMemoryConsumption()
        val driverRepository = InMemoryDriver()

        return GetTotalSpentAmountOfMoneyGroupedByMonthForDriver(
            driverRepository = driverRepository,
            consumptionRepository = repository
        ).execute(driverId = driverId)
    }
}
