package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.exception.DriverIdFailureException

data class Driver(
    val id: Long,
    val firstName: String = "",
    val lastName: String = ""
) {
    init {
        if (id <= 0) throw DriverIdFailureException("Driver id should be greater than zero")
    }
}
