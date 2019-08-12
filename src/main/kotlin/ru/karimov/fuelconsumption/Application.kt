package ru.karimov.fuelconsumption

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuelConsumptionApplication

fun main(args: Array<String>) {
    SpringApplication.run(FuelConsumptionApplication::class.java, *args)
}
