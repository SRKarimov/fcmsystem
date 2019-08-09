package ru.karimov.fuelconsumption.domain.entity

import ru.karimov.fuelconsumption.domain.exception.InvalidInputException
import java.io.Serializable
import java.time.LocalDate

class ListOfConsumption() {
    object Singleton {
        val consumptions: MutableList<Consumption> = mutableListOf()
    }

    constructor(consumption: Consumption) : this() {
        Singleton.consumptions.add(consumption)
    }

    constructor(consumptions: List<Consumption>): this() {
        if (consumptions.isEmpty()) throw InvalidInputException("List of consumptions should not be empty")
        Singleton.consumptions.addAll(consumptions)
    }

    fun save(consumption: Consumption) {
        Singleton.consumptions.add(consumption)
    }

    fun saveAll(consumptions: List<Consumption>) {
        if (consumptions.isEmpty()) throw InvalidInputException("List of consumptions should not be empty")
        Singleton.consumptions.addAll(consumptions)
    }

    fun getConsumptions(): List<Consumption> = Singleton.consumptions
    fun clearConsumptions(): Unit = Singleton.consumptions.clear()
}
