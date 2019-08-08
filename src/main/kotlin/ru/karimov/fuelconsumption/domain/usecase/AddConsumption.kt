package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.exception.DataValidationException
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository

class AddConsumption(private val repo: ConsumptionRepository) {
    fun execute(consumption: Consumption): Consumption {
        return try {
            repo.createConsumption(consumption)
        }catch (ex: DataValidationException) {
            throw ex
        }
    }
}
