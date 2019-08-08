package domain.usecase

import domain.entity.Consumption
import domain.usecase.repository.ConsumptionRepository

class GetAllConsumptions(private val consumptionRepository: ConsumptionRepository) {
    fun execute(): List<Consumption> = consumptionRepository.getAllConsumptions()
}
