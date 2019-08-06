package domain.repository

import domain.entity.Consumption

interface IConsumptionValidator {
    fun isValid(consumption: Consumption): Boolean
}