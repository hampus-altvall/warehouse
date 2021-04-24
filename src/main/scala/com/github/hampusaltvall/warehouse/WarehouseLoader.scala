package com.github.hampusaltvall.warehouse

import com.github.hampusaltvall.warehouse.inventory.Inventory
import com.github.hampusaltvall.warehouse.products.Products
import scalapb.json4s.JsonFormat
import scalapb.{GeneratedMessage, GeneratedMessageCompanion}

import scala.io.Source

trait WarehouseLoader {
  private def loadJson[A <: GeneratedMessage : GeneratedMessageCompanion](path: String): A = {
    val bufferedSource = Source.fromFile(path)
    val res = JsonFormat.fromJsonString[A](bufferedSource.getLines.mkString)
    bufferedSource.close

    res
  }

  def loadInventory(path: String): Inventory = loadJson[Inventory](path)

  def loadProducts(path: String): Products = loadJson[Products](path)
}

object WarehouseLoader extends WarehouseLoader