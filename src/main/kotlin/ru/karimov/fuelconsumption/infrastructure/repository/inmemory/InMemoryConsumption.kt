package ru.karimov.fuelconsumption.infrastructure.repository.inmemory

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import java.util.*

class InMemoryConsumption: ConsumptionRepository {
    private val inMemoryDb = hashMapOf<UUID, Consumption>()

    override fun save(consumption: Consumption): Boolean {
        if (inMemoryDb.containsKey(consumption.id)) return true

        inMemoryDb[consumption.id] = consumption
        return true
    }

    override fun saveAll(consumptions: List<Consumption>): Int {
        for (consumption in consumptions) {
            save(consumption)
        }

        return consumptions.size
    }

    override fun fetchById(id: UUID): Consumption {
        require(inMemoryDb.containsKey(id)) { "Consumption not found" }
        return inMemoryDb[id]!!
    }

    override fun fetchAll(): List<Consumption> {
        return inMemoryDb.values.toList()
    }
}
