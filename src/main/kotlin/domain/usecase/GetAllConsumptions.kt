package domain.usecase

import domain.entity.Consumption
import domain.repository.IConsumptionRepository

class GetAllConsumptions(private val consumptionRepository: IConsumptionRepository) {
    fun execute(): List<Consumption> = consumptionRepository.getAllConsumptions()
}
