import Dependencies.*

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.mattmoore"

lazy val root = (project in file("."))
  .aggregate(
    api,
    http
  )

lazy val api = (project in file("api"))
  .settings(
    name := "telemetry-api",
    libraryDependencies ++= apiDeps
  )

lazy val http = (project in file("http"))
  .settings(
    name := "telemetry-http",
    mainClass := Some("io.mattmoore.scala.telemetry.http.HttpService")
  )

lazy val integrationTests = (project in file("integration-tests"))
  .settings(
    name := "integration-tests",
    libraryDependencies ++= integrationTestDeps,
    Test / fork := true
  )
  .dependsOn(
    api
  )
