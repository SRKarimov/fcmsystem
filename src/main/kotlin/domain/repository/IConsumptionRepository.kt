package domain.repository

import domain.entity.Consumption

interface IConsumptionRepository {
    fun getAllConsumptions(): List<Consumption>
}
