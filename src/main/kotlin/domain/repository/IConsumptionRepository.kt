package domain.repository

import domain.entity.Consumption

interface IConsumptionRepository {
    fun getAllConsumptions(): List<Consumption>
    fun createConsumption(consumption: Consumption): Consumption?
    fun getConsumptionById(id: Long): Consumption?
    fun getAllConsumptionsByDriverId(driverId: Long): List<Consumption>
}
