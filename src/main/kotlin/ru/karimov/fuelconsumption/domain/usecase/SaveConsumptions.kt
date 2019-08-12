package ru.karimov.fuelconsumption.domain.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.lang.RuntimeException

class SaveConsumptions(private val consumptionRepository: ConsumptionRepository) {
    fun execute(consumptions: List<Consumption>): List<Consumption> {
        return try {
            consumptionRepository.saveAll(consumptions)
        }catch (ex: RuntimeException) {
            throw ex
        }
    }
}
