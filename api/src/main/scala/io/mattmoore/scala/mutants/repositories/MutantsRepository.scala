package io.mattmoore.scala.mutants.repositories

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.*
import com.datastax.oss.driver.api.core.metadata.EndPoint
import io.mattmoore.scala.mutants.model.Mutant

import java.net.InetSocketAddress
import scala.jdk.CollectionConverters.*

trait Repository {
  def all: List[Mutant]
  def get(id: Int): Mutant
}

class MutantsRepository(session: CqlSession) extends Repository {
  override def all: List[Mutant] =
    session
      .execute("select id, name from mutants")
      .all
      .asScala
      .toList
      .map { row =>
        Mutant(
          id = row.getInt("id"),
          name = row.getString("name")
        )
      }

  override def get(id: Int): Mutant =
    val rs = session.execute(s"select id, name from mutants where id = $id").one()
    Mutant(
      id = rs.getInt("id"),
      name = rs.getString("name")
    )
}
