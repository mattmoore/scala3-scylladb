package io.mattmoore.scala.telemetry.service

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*
import io.mattmoore.scala.telemetry.services.*

import java.net.InetSocketAddress

class TelemetryServiceSuite extends munit.FunSuite {
  val telemetryRepository = new TelemetryRepository(CqlSession.builder().build())
  val telemetryService = TelemetryService(telemetryRepository)

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
    val rs = telemetryService.all
    assertEquals(rs, expected)
  }

  test("get") {
    val result = telemetryService.get(1)
    assert(result.name == "play")
  }
}
