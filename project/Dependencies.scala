import sbt._

object Dependencies {

  object Versions {
    val scalaPB = "0.9.8"
    val scalaPBJson4s = "0.10.0"
    val scalaTest = "3.2.7"
    val scallop = "4.0.2"
  }

  val scalaPB = "com.thesamet.scalapb" %% "scalapb-runtime" % Versions.scalaPB
  val scalaPbJson4s = "com.thesamet.scalapb" %% "scalapb-json4s" % Versions.scalaPBJson4s
  val scallop = "org.rogach" %% "scallop" % Versions.scallop

  object Test {
    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % "test"
  }
}
