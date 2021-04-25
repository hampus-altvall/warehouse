lazy val commonSettings = Seq(
  name := "warehouse",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.12",
  libraryDependencies ++= Seq(
    Dependencies.scalaPB,
    Dependencies.Test.scalaTest,
    Dependencies.scalaPbJson4s,
    Dependencies.scallop
  )
)

lazy val `warehouse` = (project in file("."))
  .settings(commonSettings: _*)
  .settings(ScalaPBSettings.default: _*)
  .settings(PB.targets in Compile := Seq(
    scalapb.gen() -> (sourceManaged in Compile).value
  ))
  .settings(mainClass in assembly := Some("com.github.hampusaltvall.warehouse.Main"))

