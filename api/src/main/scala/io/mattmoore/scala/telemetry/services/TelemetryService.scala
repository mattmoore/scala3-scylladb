package io.mattmoore.scala.telemetry.services

import io.mattmoore.scala.telemetry.model.*
import io.mattmoore.scala.telemetry.repositories.*

trait Service {
  def all: List[TelemetryAction]

  def get(id: Int): TelemetryAction
}

class TelemetryService(repo: TelemetryRepository) extends Service {
  override def all: List[TelemetryAction] =
    repo.all

  override def get(id: Int): TelemetryAction =
    repo.get(id)
}
