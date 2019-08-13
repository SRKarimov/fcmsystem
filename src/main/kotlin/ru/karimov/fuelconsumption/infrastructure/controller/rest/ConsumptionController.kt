package ru.karimov.fuelconsumption.infrastructure.controller.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.*
import ru.karimov.fuelconsumption.usecase.SaveConsumption
import ru.karimov.fuelconsumption.usecase.SaveConsumptionList
import ru.karimov.fuelconsumption.infrastructure.controller.model.ConsumptionDto
import ru.karimov.fuelconsumption.infrastructure.repository.inmemory.InMemoryConsumption
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping



@RestController
@RequestMapping("/consumption")
class ConsumptionController() {
    @PostMapping("/save")
    fun create(@RequestBody consumptionDto: ConsumptionDto
    ): String {
        val repository = InMemoryConsumption()
        SaveConsumption(consumptionRepository = repository).execute(consumptionDto.toConsumption())
        return "You successfully saved!"
    }

    @PostMapping("/save_all")
    fun createBulk(@RequestParam("file") file: MultipartFile): String {
        val mapper = ObjectMapper()
        val typeRef = object : TypeReference<List<ConsumptionDto>>() {}
        val repository = InMemoryConsumption()
        val consumptionDtoList: List<ConsumptionDto> = mapper.readValue(file.inputStream, typeRef)
        SaveConsumptionList(consumptionRepository = repository).execute(consumptionDtoList.map { it -> it.toConsumption() })
        return "You successfully uploaded " + file.originalFilename + "!"
    }
}
