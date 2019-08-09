package ru.karimov.fuelconsumption.domain.entity.Dto

data class PurchaseDto (
    val fuelType: String,
    val volume: Double,
    val date: String,
    val price: Double,
    val totalPrice: Double,
    val driverId: Long
)
