package ru.karimov.fuelconsumption.adapter.repository.db
import ru.karimov.fuelconsumption.domain.entity.Consumption
import ru.karimov.fuelconsumption.domain.usecase.repository.ConsumptionRepository
import java.util.*

class InMemoryConsumptionRepository: ConsumptionRepository {
    private val inMemoryDb = hashMapOf<UUID, Consumption>()

    override fun save(consumption: Consumption): Consumption {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAll(consumptions: List<Consumption>): List<Consumption> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchById(id: UUID): Consumption {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAll(): List<Consumption> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
