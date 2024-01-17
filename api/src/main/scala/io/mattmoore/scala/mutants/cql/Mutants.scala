// package io.mattmoore.scala.mutants.cql

// import io.mattmoore.scala.mutants.model.Mutant

// abstract class Mutants extends Table[Mutants, Mutant] {
//   override def tableName: String = "mutant_data"

//   object firstName extends StringColumn with PartitionKey {
//     override def name = "first_name"
//   }

//   object lastName extends StringColumn with PartitionKey {
//     override def name = "last_name"
//   }

//   object address extends StringColumn

//   object pictureLocation extends StringColumn {
//     override def name = "picture_location"
//   }
// }
