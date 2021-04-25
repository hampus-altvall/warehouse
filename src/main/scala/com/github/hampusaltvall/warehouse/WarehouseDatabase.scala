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
        .find { case (artId, _) => artId == ca.artId }
        .map { case (_, stock) => stock / amountOf }
        .getOrElse(0)
    }.min).getOrElse(0)

  // lists available items based on current inventory.
  // Each products availability is calculated independently of other products using inventory.
  // TODO: cache result until an item is sold
  def listAllAvailableItems(): String = {
    products.products.map(product => product.name + ": " + getNumAvailable(product))
      .mkString("\n")
  }

  def sellItem(productName: String): String = {
    val productId = productName.toLowerCase

    products.products.find(_.name.toLowerCase == productId) match {
      case Some(product) =>
        var canSell = true
        var newInventoryMap = inventoryMap

        product.containArticles.foreach { ca =>
          val artId = ca.artId

          newInventoryMap = inventoryMap.map { case (inventoryArtId, stock) =>
            if (inventoryArtId == artId) {
              val newStock = stock - ca.amountOf.toInt

              if (newStock >= 0) {
                inventoryArtId -> newStock
              } else {
                canSell = false
                inventoryArtId -> stock
              }
            } else {
              inventoryArtId -> stock
            }
          }
        }
        if (canSell) {
          inventoryMap = newInventoryMap
          "Sold (1) " + productName
        } else "Can't sell " + productName + ". Not enough stock."
      case _ => "Product name not found. Use command list to see name of products."
    }
  }
}
