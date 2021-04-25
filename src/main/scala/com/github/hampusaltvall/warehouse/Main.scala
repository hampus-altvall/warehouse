package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.WarehouseLoader.{loadInventory, loadProducts}
import com.github.hampusaltvall.warehouse.configs.BootConfig

object Main extends App {
  val conf = new BootConfig(args)
  val inventory = conf.inventoryPath.toOption.map(loadInventory)
  val products = conf.productsPath.toOption.map(loadProducts)
  val help = Seq(
    "list: Get all products and quantities of the each item that is available with the current inventory.",
    "sell <Product Name>: Remove(Sell) a product and update the inventory accordingly.",
    "q: Quit",
    "help: List commands.")

  val warehouseDB = new WarehouseDatabase(inventory.head, products.head)

  println("Please enter a command...")
  println("Enter help to see available commands.")
  Iterator.continually(io.StdIn.readLine)
    .takeWhile(_ != "q")
    .foreach{
      case "list" => warehouseDB.listAllAvailableItems()
      case sellCmd if sellCmd.startsWith("sell ") => warehouseDB.sellItem(sellCmd)
      case "help" => println(help.mkString("\n"))
      case _ => println("Command not recognized. Enter help to see available commands.")
    }

  println("Bye!")
}
