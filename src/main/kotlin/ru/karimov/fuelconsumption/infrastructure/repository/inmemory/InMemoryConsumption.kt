package ru.karimov.fuelconsumption.infrastructure.repository.inmemory

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.usecase.repository.ConsumptionRepository
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class InMemoryConsumption: ConsumptionRepository {
    object Singleton {
        val inMemoryDb = hashMapOf<UUID, Consumption>()
    }

    override fun save(consumption: Consumption): Boolean {
        if (Singleton.inMemoryDb.containsKey(consumption.id)) return true

        Singleton.inMemoryDb[consumption.id] = consumption
        return true
    }

    override fun saveAll(consumptions: List<Consumption>): Int {
        for (consumption in consumptions) {
            save(consumption)
        }

        return consumptions.size
    }

    override fun fetchById(id: UUID): Consumption {
        require(Singleton.inMemoryDb.containsKey(id)) { "Consumption not found" }
        return Singleton.inMemoryDb[id]!!
    }

    override fun fetchAll(): List<Consumption> {
        return Singleton.inMemoryDb.values.toList()
    }

    override fun deleteAll() {
        Singleton.inMemoryDb.clear()
    }
}
