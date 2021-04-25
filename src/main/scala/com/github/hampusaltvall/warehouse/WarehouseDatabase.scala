package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.inventory.Inventory
import com.github.hampusaltvall.warehouse.product.Product
import com.github.hampusaltvall.warehouse.products.Products

import scala.util.Try

class WarehouseDatabase(inventory: Inventory, products: Products) {
  var inventoryMap =
    inventory.inventory.map(x => x.artId -> x.stock.toInt).toMap // would prefer to have this value as an int in json. scalapb can enforce that type

  private def getNumAvailable(product: Product): Int =
    Try(product.containArticles.map { ca =>
      val amountOf = ca.amountOf.toInt

      inventoryMap
        .find(_._1 == ca.artId)
        .map(_._2 / amountOf)
        .getOrElse(0)
    }.min).getOrElse(0)

  // lists available items based on current inventory.
  // Each products availability is calculated independently of other products using inventory.
  // TODO: cache result until an item is sold
  def listAllAvailableItems(): Unit = {
    products.products.foreach(product => println(product.name + ": " + getNumAvailable(product)))
  }

  def sellItem(productName: String): Unit = {
    val productId = productName.split(" ") // Remove "Sell " from command, generous assumption about spaced product names about product names here.
      .drop(1)
      .mkString(" ")
      .toLowerCase

    // TODO: make sure we can sell before modifying
    products.products.find(_.name.toLowerCase == productId) match {
      case Some(product) => product.containArticles.foreach { ca =>
        val artId = ca.artId

        inventoryMap = inventoryMap.map { article =>
          if (article._1 == artId) {
            article._1 -> (article._2 - ca.amountOf.toInt)
          } else {
            article
          }
        }
      }
        println("Sold (1) " + productId)
      case _ => println("Product name not found. Use command list to see name of products.")
    }

  }
}
