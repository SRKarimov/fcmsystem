package domain.usecase.repository

import domain.entity.Consumption

interface DataValidator {
    fun validate(consumption: Consumption)
}
