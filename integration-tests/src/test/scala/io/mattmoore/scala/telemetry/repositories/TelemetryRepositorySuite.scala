package io.mattmoore.scala.telemetry.repositories

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import com.dimafeng.testcontainers.ContainerDef
import com.dimafeng.testcontainers.GenericContainer
import com.dimafeng.testcontainers.munit.TestContainerForEach
import containers.ScyllaDbContainer
import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*
import munit.GenericBeforeEach
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.wait.strategy.WaitStrategy

import java.net.InetSocketAddress
import scala.concurrent.Future

class TelemetryRepositorySuite extends munit.FunSuite with TestContainerForEach {
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
      val rs = telemetryRepository.all
      assertEquals(rs, expected)
    }
  }

  test("get") {
    withContainers { container =>
      val session = CqlSession.builder().withKeyspace("telemetry").build()
      val telemetryRepository = new TelemetryRepository(session)
      val result = telemetryRepository.get(1)
      assert(result.name == "play")
    }
  }

  test("insert") {
    withContainers { container =>
      val session = CqlSession.builder().withKeyspace("telemetry").build()
      val telemetryRepository = new TelemetryRepository(session)
      val action = TelemetryAction(3, "progress")
      telemetryRepository.insert(action)
      val expected = TelemetryAction(3, "progress")
      val actual = telemetryRepository.get(3)
      assertEquals(actual, expected)
    }
  }
}
