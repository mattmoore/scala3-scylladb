package io.mattmoore.scala.telemetry.repositories

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*

import java.net.InetSocketAddress

class TelemetryRepositorySuite extends munit.FunSuite {
  val telemetryRepository = new TelemetryRepository(CqlSession.builder().build())

  test("all") {
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

  test("get") {
    val result = telemetryRepository.get(1)
    assert(result.name == "play")
  }
}
