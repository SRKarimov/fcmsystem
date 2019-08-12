package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.lang.RuntimeException

class SaveConsumption(private val consumptionRepository: ConsumptionRepository) {
    fun execute(consumption: Consumption): Consumption {
        return try {
            consumptionRepository.save(consumption)
        }catch (ex: RuntimeException) {
            throw ex
        }
    }
}
