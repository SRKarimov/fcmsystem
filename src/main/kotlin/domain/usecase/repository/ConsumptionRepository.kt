package domain.usecase.repository

import domain.entity.Consumption
import java.util.*

interface ConsumptionRepository {
    fun getAllConsumptions(): List<Consumption>
    fun getConsumptionById(id: UUID): Consumption?
    fun getAllConsumptionsByDriverId(driverId: Long): List<Consumption>
    fun createConsumption(consumption: Consumption): Consumption
    fun createConsumptionsRange(consumptions: List<Consumption>): Int
}
