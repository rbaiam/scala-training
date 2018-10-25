package lectures.part2oop

import playground.{Cindrella, PrinceCharming}

// rename the import
import java.util.Date
import java.sql.{Date => SQLDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  //import the package
  val princess = new Cindrella
  //new playground.Cindrella = fully qualified name

  //packages are ordered hierarchly

  // package object: rarely used
  sayHello

  //imports group
  val prince = new PrinceCharming

  //
  val date = new Date()
  //Use aliases
  val date2= new SQLDate(2018,5,4)
  //Use Full qualified names
  val date3= new java.sql.Date(2018,5,4)
}
