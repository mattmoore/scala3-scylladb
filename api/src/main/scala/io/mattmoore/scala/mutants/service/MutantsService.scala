package io.mattmoore.scala.mutants.service

import io.mattmoore.scala.mutants.model.*
import io.mattmoore.scala.mutants.repositories.*

trait Service {
  def all: List[Mutant]

  def get(id: Int): Mutant
}

class MutantsService(db: MutantsRepository) extends Service {
  override def all: List[Mutant] =
    db.all

  override def get(id: Int): Mutant =
    db.get(id)
}
