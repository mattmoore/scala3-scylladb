package io.mattmoore.scala.telemetry.repositories

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.*
import com.datastax.oss.driver.api.core.metadata.EndPoint
import io.mattmoore.scala.telemetry.model.TelemetryAction

import java.net.InetSocketAddress
import scala.jdk.CollectionConverters.*

trait Repository {
  def all: List[TelemetryAction]
  def get(id: Int): TelemetryAction
  def insert(action: TelemetryAction): Unit
}

class TelemetryRepository(session: CqlSession) extends Repository {
  override def all: List[TelemetryAction] =
    val cql =
      """|SELECT id, name
         |FROM telemetry
         |""".stripMargin
    val bound = session.prepare(cql).bind()
    session.execute(bound).all.asScala.toList.map { row =>
      TelemetryAction(
        id = row.getInt("id"),
        name = row.getString("name")
      )
    }

  override def get(id: Int): TelemetryAction =
    val cql =
      """|SELECT id, name
         |FROM telemetry
         |WHERE id = :id
         |""".stripMargin
    val bound = session
      .prepare(cql)
      .bind()
      .setInt("id", id)
    val rs = session.execute(bound).one()
    TelemetryAction(
      id = rs.getInt("id"),
      name = rs.getString("name")
    )

  override def insert(action: TelemetryAction): Unit =
    val cql =
      """|INSERT INTO telemetry (
         |  id,
         |  name
         |) VALUES (
         |  :id,
         |  :name
         |)
         |""".stripMargin
    val bound = session
      .prepare(cql)
      .bind()
      .setInt("id", action.id)
      .setString("name", action.name)
    session.execute(bound).getExecutionInfo()
}
