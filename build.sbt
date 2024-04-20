import sbtwelcome.*

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.mattmoore"

lazy val root = (project in file("."))
  .settings(
    welcomeSettings
  )
  .aggregate(
    core,
    storageScylla,
    http
  )

lazy val core = (project in file("core"))
  .settings(
    name := "telemetry-core",
    libraryDependencies ++= Dependencies.core
  )

lazy val storageScylla = (project in file("storage-scylla"))
  .settings(
    name := "storage-scylla",
    libraryDependencies ++= Dependencies.storageScylla
  )
  .dependsOn(
    core
  )

lazy val http = (project in file("http"))
  .settings(
    name := "telemetry-http",
    mainClass := Some("io.mattmoore.scala.telemetry.http.HttpService"),
    libraryDependencies ++= Dependencies.http
  )

lazy val integrationTests = (project in file("integration-tests"))
  .settings(
    name := "integration-tests",
    libraryDependencies ++= Dependencies.integrationTest,
    Test / fork := true
  )
  .dependsOn(
    core,
    storageScylla,
    http
  )

lazy val welcomeSettings = Seq(
  logo := Embroidery.getLogo,
  usefulTasks := Seq(
    UsefulTask("~compile", "Compile with file-watch enabled."),
    UsefulTask("fmt", "Run scalafmt on the entire project.")
  )
)
