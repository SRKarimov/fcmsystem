package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class GetAllConsumptionsByDriverId (private val consumptionRepository: ConsumptionRepository) {
    fun execute(driverId: Long): List<Consumption> = consumptionRepository.getAllConsumptionsByDriverId(driverId)
}
