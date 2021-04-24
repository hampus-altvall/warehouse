name := "warehouse"

version := "0.1"

scalaVersion := "2.13.5"

lazy val commonSettings = Seq(
  name := "warehouse",
  version := "0.1-SNAPSHOT",
  libraryDependencies ++= Seq(
    Dependencies.scalaPB,
    Dependencies.Test.scalaTest,
    Dependencies.scalaPbJson4s,
    Dependencies.scalaSbtCommand
  )
)

lazy val `warehouse` = (project in file("."))
  .settings(commonSettings: _*)
  .settings(ScalaPBSettings.default: _*)
  .settings(PB.targets in Compile := Seq(
    scalapb.gen() -> (sourceManaged in Compile).value
  ))

