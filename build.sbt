name := """MyApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  jdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.typesafe.play.modules" %% "play-modules-redis" % "2.5.0"
)

resolvers ++= Seq(
    "google-sedis-fix" at "http://pk11-scratch.googlecode.com/svn/trunk"
)

import com.typesafe.sbt.packager.MappingsHelper._
  mappings in Universal ++= directory(baseDirectory.value / "public")
