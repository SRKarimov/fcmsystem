package domain.usecase

import domain.entity.Consumption
import domain.usecase.exception.DataValidationException
import domain.usecase.repository.ConsumptionRepository
import domain.usecase.repository.DataValidator

class AddConsumption(private val repo: ConsumptionRepository) {
    fun execute(consumption: Consumption): Consumption {
        return try {
            repo.createConsumption(consumption)
        }catch (ex: DataValidationException) {
            throw ex
        }
    }
}
