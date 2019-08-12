package ru.karimov.fuelconsumption.infrastructure.controller.model

data class PurchaseDto (
    val fuelType: String,
    val volume: Double,
    val date: String,
    val price: Double,
    val totalPrice: Double,
    val driverId: Long
)
