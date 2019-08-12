package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import java.lang.RuntimeException

class SaveConsumption(private val consumptionRepository: ConsumptionRepository) {
    fun execute(consumption: Consumption): Boolean {
        return try {
            consumptionRepository.save(consumption)
        }catch (ex: RuntimeException) {
            throw ex
        }
    }
}
