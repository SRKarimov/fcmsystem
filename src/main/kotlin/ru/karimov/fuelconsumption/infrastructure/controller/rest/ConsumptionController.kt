package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.usecase.SaveConsumption
import ru.karimov.fuelconsumption.usecase.SaveConsumptionList
import ru.karimov.fuelconsumption.infrastructure.controller.model.ConsumptionDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption

@RestController
@RequestMapping("/consumption")
class ConsumptionController() {
    @PostMapping("/save")
    fun create(@RequestBody consumptionDto: ConsumptionDto) {
        val repository = InMemoryConsumption()
        val result =  SaveConsumption(consumptionRepository = repository).execute(consumptionDto.toConsumption())
    }

    @PostMapping("/save_all")
    fun createBulk(@RequestBody consumptionDtoList: List<ConsumptionDto>) {
        val repository = InMemoryConsumption()
        val result = SaveConsumptionList(consumptionRepository = repository).execute(consumptionDtoList.map { it -> it.toConsumption() })
    }
}
