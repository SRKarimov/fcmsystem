package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonth
import ru.karimov.fuelconsumption.usecase.GetTotalSpentAmountOfMoneyGroupedByMonthForDriver

@RestController
@RequestMapping("/total_spent_money")
class TotalSpentMoneyController {
    @GetMapping("/")
    @ResponseBody
    fun create(): Map<String, Double> {
        val repository = InMemoryConsumption()

        return GetTotalSpentAmountOfMoneyGroupedByMonth(repository).execute()
    }

    @GetMapping("/{driverId}")
    @ResponseBody
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
