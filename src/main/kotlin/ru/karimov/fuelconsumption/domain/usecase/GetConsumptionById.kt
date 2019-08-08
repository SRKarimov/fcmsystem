package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.exception.ConsumptionNotFoundException
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.util.*

class GetConsumptionById(private val consumptionRepository: ConsumptionRepository) {
    fun execute(id: UUID): Consumption = consumptionRepository.getConsumptionById(id) ?: throw ConsumptionNotFoundException("Consumption not found")
}
