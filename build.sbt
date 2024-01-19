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
    libraryDependencies ++=
      scylladb ++
        logging ++
        testDeps
  )

lazy val http = (project in file("http"))
  .settings(
    name := "telemetry-http",
    mainClass := Some("io.mattmoore.scala.telemetry.http.HttpService")
  )
