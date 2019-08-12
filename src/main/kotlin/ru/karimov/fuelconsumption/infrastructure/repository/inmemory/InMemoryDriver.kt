package ru.karimov.fuelconsumption.infrastructure.repository.inmemory

import ru.karimov.fuelconsumption.domain.entity.Driver
import ru.karimov.fuelconsumption.domain.usecase.repository.DriverRepository

class InMemoryDriver: DriverRepository {
    private val inMemoryDb = hashMapOf<Long, Driver>()

    override fun save(driver: Driver): Driver {
        if (inMemoryDb.containsKey(driver.id)) return driver

        inMemoryDb[driver.id] = driver
        return driver
    }

    override fun saveAll(drivers: List<Driver>): List<Driver> {
        for (driver in drivers) {
            save(driver)
        }

        return drivers
    }

    override fun fetchById(id: Long): Driver {
        require(inMemoryDb.containsKey(id)) { "Consumption not found" }
        return inMemoryDb[id]!!
    }

    override fun fetchAll(): List<Driver> {
        return inMemoryDb.values.toList()
    }
}
