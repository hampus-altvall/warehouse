import com.github.hampusaltvall.warehouse.{WarehouseDatabase, WarehouseLoader}
import com.github.hampusaltvall.warehouse.inventory.Inventory
import com.github.hampusaltvall.warehouse.products.Products
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class WarehouseDatabaseTest extends AnyFunSuite with BeforeAndAfter {
  var inventory: Inventory = null
  var products: Products = null
  var warehouseDatabase: WarehouseDatabase = null

  before {
    inventory = WarehouseLoader.loadInventory("src/test/resources/inventory.json")
    products = WarehouseLoader.loadProducts("src/test/resources/products.json")
    warehouseDatabase = new WarehouseDatabase(inventory, products)
  }

  test("WarehouseDatabase.listAllAvailableItems") {
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 2\nDinning Table: 1")
  }

  test("WarehouseDatabase.sellItem") {
    assert(warehouseDatabase.sellItem("dinning table") == "Sold (1) dinning table")
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 2\nDinning Table: 0")

    assert(warehouseDatabase.sellItem("dinning table") == "Can't sell dinning table. Not enough stock.")
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 2\nDinning Table: 0")

    assert(warehouseDatabase.sellItem("Graphics Card") == "Product name not found. Use command list to see name of products.")

    assert(warehouseDatabase.sellItem("Dining Chair") == "Sold (1) Dining Chair")
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 1\nDinning Table: 0")

    assert(warehouseDatabase.sellItem("Dining Chair") == "Sold (1) Dining Chair")
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 0\nDinning Table: 0")

    assert(warehouseDatabase.sellItem("Dining Chair") == "Can't sell Dining Chair. Not enough stock.")
    assert(warehouseDatabase.listAllAvailableItems() == "Dining Chair: 0\nDinning Table: 0")
  }
}
