name := "warehouse"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("com.github.warehouse")

libraryDependencies += "com.thesamet.scalapb" %% "scalapb-json4s" % "0.10.1"

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)