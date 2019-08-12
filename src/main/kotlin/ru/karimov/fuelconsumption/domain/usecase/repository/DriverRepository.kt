package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Driver

interface DriverRepository {
    fun fetchById(id: Long): Driver
}
