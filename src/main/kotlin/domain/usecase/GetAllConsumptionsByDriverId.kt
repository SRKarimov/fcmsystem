package domain.usecase

import domain.entity.Consumption
import domain.usecase.repository.ConsumptionRepository

class GetAllConsumptionsByDriverId (private val consumptionRepository: ConsumptionRepository) {
    fun execute(driverId: Long): List<Consumption> = consumptionRepository.getAllConsumptionsByDriverId(driverId)
}
