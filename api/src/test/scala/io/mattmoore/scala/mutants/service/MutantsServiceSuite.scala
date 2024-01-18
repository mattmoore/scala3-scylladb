package io.mattmoore.scala.mutants.cql

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import io.mattmoore.scala.mutants.model.Mutant
import io.mattmoore.scala.mutants.service.MutantsService

import java.net.InetSocketAddress

class MutantsServiceSuite extends munit.FunSuite {
  val mutantsDb = new MutantsDatabase(CqlSession.builder().build())
  val mutantsService = MutantsService(mutantsDb)

  test("all") {
    val expected = List(
      Mutant(
        id = 1,
        name = "Godzilla"
      ),
      Mutant(
        id = 2,
        name = "Scylla"
      )
    )
    val rs = mutantsService.all
    assertEquals(rs, expected)
  }

  test("get") {
    val result = mutantsService.get(1)
    assert(result.name == "Godzilla")
  }
}
