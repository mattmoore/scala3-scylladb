package io.mattmoore.scala.mutants.repositories

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import io.mattmoore.scala.mutants.model.*

import java.net.InetSocketAddress

class MutantsRepositorySuite extends munit.FunSuite {
  val mutantsRepository = new MutantsRepository(CqlSession.builder().build())

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
    val rs = mutantsRepository.all
    assertEquals(rs, expected)
  }

  test("get") {
    val result = mutantsRepository.get(1)
    assert(result.name == "Godzilla")
  }
}
