package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Driver

interface DriverRepository {
    fun fetchById(id: Long): Driver
    fun save(driver: Driver): Driver
    fun saveAll(drivers: List<Driver>): List<Driver>
    fun fetchAll(): List<Driver>
}
