package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption
import java.util.*

interface ConsumptionRepository {
    fun save(consumption: Consumption): Consumption
    fun saveAll(consumptions: List<Consumption>): List<Consumption>
    fun fetchById(id: UUID): Consumption
    fun fetchAll(): List<Consumption>
}
