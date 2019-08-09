package ru.karimov.fuelconsumption.adapter.repository.db
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.util.*

class InMemoryConsumptionRepository: ConsumptionRepository {
    private val inMemoryDb = hashMapOf<UUID, Consumption>()

    override fun getAllConsumptions(): List<Consumption> {
        return inMemoryDb.values.toList()
    }

    override fun getConsumptionById(id: UUID): Consumption? {
        if (!inMemoryDb.containsKey(id)) return null
        return inMemoryDb[id]
    }

    override fun getAllConsumptionsByDriverId(driverId: Long): List<Consumption> {
        return inMemoryDb.values.filter { it -> it.driver.id == driverId }.toList()
    }

    override fun createConsumption(consumption: Consumption): Consumption {
        try {
            inMemoryDb[consumption.id] = consumption
        }catch (ex: Exception) {
            throw ex
        }

        return consumption
    }

    override fun createConsumptionsRange(consumptions: List<Consumption>): Int {
        for (consumption in consumptions) {
            inMemoryDb[consumption.id] = consumption
        }

        return consumptions.size
    }
}
