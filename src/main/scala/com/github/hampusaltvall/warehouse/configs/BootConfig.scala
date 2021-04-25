package com.github.hampusaltvall.warehouse.configs

import org.rogach.scallop.{ScallopConf, ScallopOption}

class BootConfig(arguments: Seq[String]) extends ScallopConf(arguments) {
  val inventoryPath: ScallopOption[String] = opt[String](
    name = "inventory-path",
    descr = "Path to inventory json file.",
    required = true
  )
  val productsPath: ScallopOption[String] = opt[String](
    name = "products-path",
    descr = "Path to products json file.",
    required = true
  )
  verify()
}