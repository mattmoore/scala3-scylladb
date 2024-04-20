import sbt.*

object Dependencies {
  lazy val cats = Seq(
    "org.typelevel" %% "cats-effect" % Versions.catsCore,
    "org.typelevel" %% "cats-effect" % Versions.catsEffect
  )

  lazy val scylladb = Seq(
    "com.scylladb" % "java-driver-core" % Versions.scylladb
  )

  lazy val logging = Seq(
    "org.typelevel" %% "log4cats-slf4j" % Versions.log4cats,
    "ch.qos.logback" % "logback-classic" % Versions.logback
  )

  lazy val core =
    scylladb ++
      logging ++
      unitTest

  lazy val http = Seq()

  lazy val storageScylla =
    scylladb ++
      logging ++
      unitTest

  lazy val unitTest = Seq(
    "org.scalameta" %% "munit" % Versions.munit
  )

  lazy val integrationTest = unitTest ++ Seq(
    "com.dimafeng" %% "testcontainers-scala-munit" % Versions.testcontainersScala % Test
  )
}
