package domain.usecase

import domain.entity.Consumption
import domain.usecase.exception.ConsumptionNotFoundException
import domain.usecase.repository.ConsumptionRepository
import java.util.*

class GetConsumptionById(private val consumptionRepository: ConsumptionRepository) {
    fun execute(id: UUID): Consumption = consumptionRepository.getConsumptionById(id) ?: throw ConsumptionNotFoundException("Consumption not found")
}
