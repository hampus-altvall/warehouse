package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.WarehouseLoader.{loadInventory, loadProducts}

object Main extends App {
  val inventory = loadInventory("src/test/resources/inventory.json")
  val products = loadProducts("src/test/resources/products.json")

  println(inventory.inventory.map(_.name).mkString(", "))
  println(products.products.map(_.name).mkString(", "))
}
