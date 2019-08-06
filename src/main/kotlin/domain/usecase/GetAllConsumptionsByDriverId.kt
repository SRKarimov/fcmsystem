package domain.usecase

import domain.entity.Consumption
import domain.repository.IConsumptionRepository

class GetAllConsumptionsByDriverId (private val consumptionRepository: IConsumptionRepository) {
    fun execute(driverId: Long): List<Consumption> = consumptionRepository.getAllConsumptionsByDriverId(driverId)
}
