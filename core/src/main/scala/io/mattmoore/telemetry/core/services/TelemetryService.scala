package io.mattmoore.telemetry.core.services

import io.mattmoore.telemetry.core.domain.TelemetryAction
import io.mattmoore.telemetry.core.repositories.TelemetryRepository

trait TelemetryService {
  def all: List[TelemetryAction]

  def get(id: Int): TelemetryAction
}

object TelemetryService {
  def apply(repo: TelemetryRepository) = new TelemetryService {
    override def all: List[TelemetryAction] =
      repo.all

    override def get(id: Int): TelemetryAction =
      repo.get(id)
  }
}
