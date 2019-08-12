package ru.karimov.fuelconsumption.domain.entity

data class Purchase (
    val fuelType: String,
    val volume: Double,
    val date: String,
    val price: Double,
    val totalPrice: Double,
    val driverId: Long
){
    init {
        require(volume > 0) { "Volume should be greater than zero" }
        require(price > 0) { "Price per litter should be greater than zero" }
        require(totalPrice > 0) { "Total price per litter should be greater than zero" }
    }
}
