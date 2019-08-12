package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.infrastructure.controller.model.PurchaseDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryDriver
import ru.karimov.fuelconsumption.usecase.GetListOfPurchasesForMonth
import ru.karimov.fuelconsumption.usecase.GetListOfPurchasesForMonthAndDriver

@Controller
@RequestMapping("/purchase")
class PurchaseController {
    @GetMapping("/{month}")
    fun create(@PathVariable("month") month: String): List<PurchaseDto> {
        val repository = InMemoryConsumption()

        return GetListOfPurchasesForMonth(repository).execute(month = month)
            .map { it -> PurchaseDto(
                fuelType = it.fuelType,
                driverId = it.driverId,
                date = it.date,
                price = it.price,
                totalPrice = it.totalPrice,
                volume = it.volume
                ) }
    }

    @GetMapping("/{driverId}/{month}")
    fun createBulk(
        @PathVariable("driverId") driverId: Long,
        @PathVariable("month") month: String
    ): List<PurchaseDto> {
        val repository = InMemoryConsumption()
        val driverRepository = InMemoryDriver()

        return GetListOfPurchasesForMonthAndDriver(driverRepository = driverRepository, consumptionRepository = repository)
            .execute(driverId = driverId, month = month)
            .map { it -> PurchaseDto(
                fuelType = it.fuelType,
                driverId = it.driverId,
                date = it.date,
                price = it.price,
                totalPrice = it.totalPrice,
                volume = it.volume
            ) }
    }
}
