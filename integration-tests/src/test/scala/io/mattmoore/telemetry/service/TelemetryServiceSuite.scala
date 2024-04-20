package io.mattmoore.telemetry.service

import com.datastax.oss.driver.api.core.CqlSession
import com.dimafeng.testcontainers.munit.TestContainerForEach
import containers.ScyllaDbContainer
import io.mattmoore.telemetry.core.domain.TelemetryAction
import io.mattmoore.telemetry.core.services.TelemetryService
import io.mattmoore.telemetry.repositories.scylladb.TelemetryRepository

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
    ()

  test("all") {
    withContainers { container =>
      val session = CqlSession.builder().withKeyspace("telemetry").build()
      val telemetryRepository = TelemetryRepository(session)
      val telemetryService = TelemetryService(telemetryRepository)
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
      val telemetryRepository = TelemetryRepository(session)
      val telemetryService = TelemetryService(telemetryRepository)
      val result = telemetryService.get(1)
      assert(result.name == "play")
    }
  }
}
