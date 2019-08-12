package ru.karimov.fuelconsumption.domain.entity

data class Driver(
    val id: Long,
    val firstName: String = "",
    val lastName: String = ""
) {
    init {
        require (id > 0) { "Driver id should be greater than zero" }
    }
}
