package io.mattmoore.telemetry.core.repositories

import io.mattmoore.telemetry.core.domain.TelemetryAction

trait TelemetryRepository {
  def all: List[TelemetryAction]
  def get(id: Int): TelemetryAction
  def insert(action: TelemetryAction): Unit
}
