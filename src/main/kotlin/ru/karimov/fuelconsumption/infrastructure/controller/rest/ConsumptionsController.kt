package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.usecase.SaveConsumption
import ru.karimov.fuelconsumption.usecase.SaveConsumptions
import ru.karimov.fuelconsumption.infrastructure.controller.model.ConsumptionDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption

@RestController
@RequestMapping("/consumption")
class ConsumptionsController() {
    @PostMapping("/save")
    fun create(@RequestBody consumptionDto: ConsumptionDto) {
        val repository = InMemoryConsumption()
        val result =  SaveConsumption(consumptionRepository = repository).execute(consumptionDto.toConsumption())
    }

    @PostMapping("/save_all")
    fun createBulk(@RequestBody consumptionsDto: List<ConsumptionDto>) {
        val repository = InMemoryConsumption()
        val result = SaveConsumptions(consumptionRepository = repository).execute(consumptionsDto.map { it -> it.toConsumption() })
    }
}
