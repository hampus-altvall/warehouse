package com.github.warehouse

import com.github.warehouse.inventory.Inventory
import scalapb.json4s.JsonFormat

object Main extends App {
    val proto: Inventory = JsonFormat.fromJsonString[Inventory]("""{
                                                                  |  "inventory": [
                                                                  |    {
                                                                  |      "art_id": "1",
                                                                  |      "name": "leg",
                                                                  |      "stock": "12"
                                                                  |    },
                                                                  |    {
                                                                  |      "art_id": "2",
                                                                  |      "name": "screw",
                                                                  |      "stock": "17"
                                                                  |    },
                                                                  |    {
                                                                  |      "art_id": "3",
                                                                  |      "name": "seat",
                                                                  |      "stock": "2"
                                                                  |    },
                                                                  |    {
                                                                  |      "art_id": "4",
                                                                  |      "name": "table top",
                                                                  |      "stock": "1"
                                                                  |    }
                                                                  |  ]
                                                                  |}
                                                                  |""".stripMargin)

  println("HELLO")
}
