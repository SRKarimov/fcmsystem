package ru.karimov.fuelconsumption.infrastructure.repository.inmemory

import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.usecase.repository.DriverRepository
import java.util.*

class InMemoryDriver: DriverRepository {
    object Singleton {
        val inMemoryDb = hashMapOf<Long, Driver>()
    }

    override fun save(driver: Driver): Driver {
        if (Singleton.inMemoryDb.containsKey(driver.id)) return driver

        Singleton.inMemoryDb[driver.id] = driver
        return driver
    }

    override fun saveAll(drivers: List<Driver>): List<Driver> {
        for (driver in drivers) {
            save(driver)
        }

        return drivers
    }

    override fun fetchById(id: Long): Driver {
        require(Singleton.inMemoryDb.containsKey(id)) { "Driver not found" }
        return Singleton.inMemoryDb[id]!!
    }

    override fun fetchAll(): List<Driver> {
        return Singleton.inMemoryDb.values.toList()
    }

    override fun deleteAll() {
        Singleton.inMemoryDb.clear()
    }
}
