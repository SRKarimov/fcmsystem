package ru.karimov.fuelconsumption.domain.usecase.repository

import ru.karimov.fuelconsumption.domain.entity.Consumption

interface DataValidator {
    fun validate(consumption: Consumption)
}
