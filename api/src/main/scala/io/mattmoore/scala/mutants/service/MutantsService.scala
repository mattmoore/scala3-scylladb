package io.mattmoore.scala.mutants.service

import io.mattmoore.scala.mutants.cql.MutantsDatabase
import io.mattmoore.scala.mutants.model.Mutant

class MutantsService(db: MutantsDatabase) {
  def all: List[Mutant] =
    db.all

  def get(id: Int): Mutant =
    db.get(id)
}
