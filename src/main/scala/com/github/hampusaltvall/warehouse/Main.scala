package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.WarehouseLoader.{loadInventory, loadProducts}
import com.github.hampusaltvall.warehouse.configs.BootConfig

object Main extends App {
  val conf = new BootConfig(args)
  val inventory = conf.inventoryPath.toOption.map(loadInventory)
  val products = conf.productsPath.toOption.map(loadProducts)

  println(inventory.toSeq.flatMap(_.inventory).map(_.name).mkString(", "))
  println(products.toSeq.flatMap(_.products).map(_.name).mkString(", "))
}
