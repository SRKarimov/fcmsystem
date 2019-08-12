package ru.karimov.fuelconsumption.usecase

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import java.lang.RuntimeException

class SaveConsumptionList(private val consumptionRepository: ConsumptionRepository) {
    fun execute(consumptions: List<Consumption>): Int {
        return try {
            consumptionRepository.saveAll(consumptions)
        }catch (ex: RuntimeException) {
            throw ex
        }
    }
}
