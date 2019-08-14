package ru.karimov.fuelconsumption.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Driver

interface DriverRepository {
    fun fetchById(id: Long): Driver
    fun save(driver: Driver): Driver
    fun saveAll(drivers: List<Driver>): List<Driver>
    fun fetchAll(): List<Driver>
    fun deleteAll()
}
