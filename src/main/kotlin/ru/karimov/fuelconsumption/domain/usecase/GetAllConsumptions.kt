package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetAllConsumptions(private val consumptionRepository: ConsumptionRepository) {
    fun execute(): List<Consumption> = consumptionRepository.getAllConsumptions()
}
