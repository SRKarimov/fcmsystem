package ru.karimov.fuelconsumption.infrastructure.repository.inmemory

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.util.*

class InMemoryConsumption: ConsumptionRepository {
    private val inMemoryDb = hashMapOf<UUID, Consumption>()

    override fun save(consumption: Consumption): Consumption {
        if (inMemoryDb.containsKey(consumption.id)) return consumption

        inMemoryDb[consumption.id] = consumption
        return consumption
    }

    override fun saveAll(consumptions: List<Consumption>): List<Consumption> {
        for (consumption in consumptions) {
            save(consumption)
        }

        return consumptions
    }

    override fun fetchById(id: UUID): Consumption {
        require(inMemoryDb.containsKey(id)) { "Consumption not found" }
        return inMemoryDb[id]!!
    }

    override fun fetchAll(): List<Consumption> {
        return inMemoryDb.values.toList()
    }
}
