package io.mattmoore.scala.mutants.cql

import com.datastax.oss.driver.api.core.CqlSession
import io.mattmoore.scala.mutants.model.Mutant

import java.net.InetSocketAddress

class MutantsDatabaseSuite extends munit.FunSuite {
  val session = CqlSession
    .builder()
    .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
    .withLocalDatacenter("datacenter1")
    .withKeyspace("monster")
    .build()
  val mutantsDb = new MutantsDatabase(session)

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
