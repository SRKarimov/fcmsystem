package ru.karimov.fuelconsumption.domain.entity

data class Statistics(
    val volume: Double,
    val averagePrice: Double,
    val totalPrice: Double
){
    init {
        require(volume > 0) { "Volume should be greater than zero" }
        require(averagePrice > 0) { "Average price should be greater than zero" }
        require(totalPrice > 0) { "Total price per litter should be greater than zero" }
    }
}
