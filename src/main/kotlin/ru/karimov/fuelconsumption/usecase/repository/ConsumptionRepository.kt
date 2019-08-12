package ru.karimov.fuelconsumption.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption
import java.util.*

interface ConsumptionRepository {
    fun save(consumption: Consumption): Boolean
    fun saveAll(consumptions: List<Consumption>): Int
    fun fetchById(id: UUID): Consumption
    fun fetchAll(): List<Consumption>
}
