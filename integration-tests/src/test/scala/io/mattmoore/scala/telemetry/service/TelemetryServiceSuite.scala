package io.mattmoore.scala.telemetry.service

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import com.dimafeng.testcontainers.munit.TestContainerForEach
import containers.ScyllaDbContainer
import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*
import io.mattmoore.scala.telemetry.services.*

import java.net.InetSocketAddress

class TelemetryServiceSuite extends munit.FunSuite with TestContainerForEach {
  override val containerDef: ScyllaDbContainer.Def =
    ScyllaDbContainer.Def(9042)

  override def beforeTest(containers: ScyllaDbContainer): Unit =
    val session = CqlSession.builder().build()
    session.execute(
      """|CREATE KEYSPACE telemetry
         |WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};""".stripMargin
    )
    session.execute("USE telemetry;")
    session.execute("CREATE TABLE telemetry (id INT PRIMARY KEY, name TEXT);")
    session.execute("INSERT INTO telemetry (id, name) VALUES (1, 'play');")
    session.execute("INSERT INTO telemetry (id, name) VALUES (2, 'pause');")

  test("all") {
    withContainers { container =>
      val session = CqlSession.builder().withKeyspace("telemetry").build()
      val telemetryRepository = new TelemetryRepository(session)
      val telemetryService = new TelemetryService(telemetryRepository)
      val expected = List(
        TelemetryAction(
          id = 1,
          name = "play"
        ),
        TelemetryAction(
          id = 2,
          name = "pause"
        )
      )
      val actual = telemetryService.all
      assertEquals(actual, expected)
    }
  }

  test("get") {
    withContainers { container =>
      val session = CqlSession.builder().withKeyspace("telemetry").build()
      val telemetryRepository = new TelemetryRepository(session)
      val telemetryService = new TelemetryService(telemetryRepository)
      val result = telemetryService.get(1)
      assert(result.name == "play")
    }
  }
}
