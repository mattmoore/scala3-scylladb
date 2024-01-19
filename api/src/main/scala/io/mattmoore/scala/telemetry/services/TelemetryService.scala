package io.mattmoore.scala.telemetry.services

import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*

trait Service {
  def all: List[TelemetryAction]

  def get(id: Int): TelemetryAction
}

class TelemetryService(db: TelemetryRepository) extends Service {
  override def all: List[TelemetryAction] =
    db.all

  override def get(id: Int): TelemetryAction =
    db.get(id)
}
