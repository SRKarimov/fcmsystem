package domain.repoisitory

import domain.entity.Consumption

interface IConsumptionRepository {
    fun createConsumption(consumption: Consumption): Consumption
    fun getConsumptionById(id: Long): Consumption?
    fun findConsumptionsByDriverId(driverId: Long): List<Consumption>
    fun findConsumptions(): List<Consumption>
}
