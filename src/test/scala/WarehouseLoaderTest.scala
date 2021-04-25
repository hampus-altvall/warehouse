import com.github.hampusaltvall.warehouse.WarehouseLoader
import org.scalatest.funsuite.AnyFunSuite

class WarehouseLoaderTest extends AnyFunSuite {
  test("WarehouseLoader.loadInventory") {
    val inventory = WarehouseLoader.loadInventory("src/test/resources/inventory.json")
    assert(inventory.inventory.size == 4)
    assert(inventory.inventory.map(_.name).toSet == Set("leg", "screw", "seat", "table top"))
  }

  test("WarehouseLoader.loadProducts") {
    val products = WarehouseLoader.loadProducts("src/test/resources/products.json")
    assert(products.products.size == 2)
    assert(products.products.map(_.name).toSet == Set("Dining Chair", "Dinning Table"))
  }
}
