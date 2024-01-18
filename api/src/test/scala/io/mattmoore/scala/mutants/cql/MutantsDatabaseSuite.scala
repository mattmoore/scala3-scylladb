package io.mattmoore.scala.mutants.cql

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.config.DriverConfigLoader
import io.mattmoore.scala.mutants.model.Mutant

import java.io.File
import java.net.InetSocketAddress

class MutantsDatabaseSuite extends munit.FunSuite {
  val mutantsDb = new MutantsDatabase(CqlSession.builder().build())

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
    val rs = mutantsDb.all
    assertEquals(rs, expected)
  }

  test("get") {
    val result = mutantsDb.get(1)
    assert(result.name == "Godzilla")
  }
}
