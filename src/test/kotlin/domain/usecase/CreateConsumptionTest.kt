package domain.usecase

import domain.entity.Consumption
import domain.repository.IConsumptionRepository

class CreateConsumptionTest(private val consumptionRepository: IConsumptionRepository) {
    fun execute(consumption: Consumption): Consumption? = consumptionRepository.createConsumption(consumption)
}
