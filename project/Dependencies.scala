import sbt._

object Dependencies {

  object Versions {
    val scalaPB = "0.11.1"
    val scalaPBJson4s = "0.11.0"
    val scalaTest = "3.2.7"
    val scalaLauncherInterface = "1.3.0"
  }

  val scalaPB = "com.thesamet.scalapb" %% "scalapb-runtime" % Versions.scalaPB
  val scalaPbJson4s = "com.thesamet.scalapb" %% "scalapb-json4s" % Versions.scalaPBJson4s
  val scalaSbtCommand = "org.scala-sbt" % "launcher-interface" % Versions.scalaLauncherInterface % "provided"

  object Test {
    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % "test"
  }
}
