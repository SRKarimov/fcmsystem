package ru.karimov.fuelconsumption

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuelConsumptionApplication {
    private val log = LoggerFactory.getLogger(FuelConsumptionApplication::class.java)
}

fun main(args: Array<String>) {
    runApplication<FuelConsumptionApplication>(*args)
}
