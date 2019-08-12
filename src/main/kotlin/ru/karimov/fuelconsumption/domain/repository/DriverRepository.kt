package ru.karimov.fuelconsumption.domain.repository

import ru.karimov.fuelconsumption.domain.entity.Driver

interface DriverRepository {
    fun fetchById(id: Long): Driver
}
