package domain.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ConsumptionManagerTest {
    @Test
    fun `should create instance of manager`() {
        val manager = ConsumptionManager()
        Assertions.assertNotNull(manager)
    }

    @Test
    fun `should add consumption to manager`() {
        val manager = ConsumptionManager()
        Assertions.assertNotNull(manager)
        val item = manager.add(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L))
        Assertions.assertEquals(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L), item)
    }

    @Test
    fun `should get consumption by id = 1`() {
        val manager = ConsumptionManager()
        Assertions.assertNotNull(manager)
        manager.add(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L))
        val item = manager.getById(1L)
        Assertions.assertEquals(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L), item)
    }

    @Test
    fun `should get all consumption records`() {
        val manager = ConsumptionManager()
        Assertions.assertNotNull(manager)
        manager.add(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L))
        manager.add(Consumption(2L, FuelType.Diesel, 1.55, 11.5, LocalDate.now(), 12346L))
        manager.add(Consumption(3L, FuelType.RON91, 1.65, 12.0, LocalDate.now(), 12355L))
        manager.add(Consumption(4L, FuelType.RON92, 1.75, 12.5, LocalDate.now(), 12445L))
        manager.add(Consumption(5L, FuelType.RON98, 1.85, 13.0, LocalDate.now(), 12345L))
        val items = manager.getAll()
        Assertions.assertEquals(5, items.size)
    }

    @Test
    fun `should get two items by driver id 12345`() {
        val manager = ConsumptionManager()
        Assertions.assertNotNull(manager)
        manager.add(Consumption(1L, FuelType.RON95, 1.45, 11.0, LocalDate.now(), 12345L))
        manager.add(Consumption(2L, FuelType.Diesel, 1.55, 11.5, LocalDate.now(), 12346L))
        manager.add(Consumption(3L, FuelType.RON91, 1.65, 12.0, LocalDate.now(), 12355L))
        manager.add(Consumption(4L, FuelType.RON92, 1.75, 12.5, LocalDate.now(), 12445L))
        manager.add(Consumption(5L, FuelType.RON98, 1.85, 13.0, LocalDate.now(), 12345L))
        val items = manager.getAllByDriverId(12345L)
        Assertions.assertEquals(2, items.size)
    }
}
