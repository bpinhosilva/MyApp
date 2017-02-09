name := """MyApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

import com.typesafe.sbt.packager.MappingsHelper._
  mappings in Universal ++= directory(baseDirectory.value / "public")
