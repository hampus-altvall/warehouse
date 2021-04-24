name := "warehouse"

version := "0.1"

scalaVersion := "2.13.5"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

lazy val commonSettings = Seq(
  version := "0.1",
  libraryDependencies ++= Seq(
    Dependencies.scalaPB,
    Dependencies.Test.scalaTest
  )
)

lazy val `warehouse` = (project in file("."))
  .settings(commonSettings: _*)
  .settings(ScalaPBSettings.default: _*)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalaPbJson4s
    )
  )

