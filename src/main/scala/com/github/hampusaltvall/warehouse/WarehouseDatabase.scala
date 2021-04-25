package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.inventory.Inventory
import com.github.hampusaltvall.warehouse.products.Products
import com.github.hampusaltvall.warehouse.product.Product

import scala.util.Try

class WarehouseDatabase(inventory: Inventory, products: Products) {

  private def getNumAvailable(product: Product): Int =
    Try(product.containArticles.map { ca =>
      val amountOf = ca.amountOf.toInt // would prefer to have this value as an int in json. scalapb can enforce that type

      inventory.inventory
        .find(_.artId == ca.artId)
        .map(_.stock.toInt / amountOf).getOrElse(0)
    }.min).getOrElse(0)

  // lists available items based on current inventory.
  // Each products availability is calculated independently of other products using inventory.
  def listAllAvailableItems(): Unit = {
    products.products.foreach(product => println("art_id: " + product + product.name + ": " + getNumAvailable(product)))
  }

  def sellItem(): Unit = {

  }
}
