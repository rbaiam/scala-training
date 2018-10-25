package lectures.part2oop

object CaseClasses extends App {


  /*
   No need to reimplment all default methods in case classes: equals, hashcode, toString
   */

  case class Person(name: String, age: Int)

  //1. in case class, class paramaters are fields
  val jim = new Person("Jim", 34)

  println(jim.name) // no compilation error since it is a field

  //2. sensible toString

  println(jim) // Person(Jim,34)
  // without case keyword result will be: lectures.part2oop.CaseClasses$Person@7c75222b

  //3. equals and hashcode are implemented

  val jim2 = new Person("Jim", 34)

  println(jim2 == jim) // true

  // 4. Case classes have handy copy method

  val jim3 = jim.copy()
  val jimWithDifferentAge = jim.copy(age = 53)

  // 5. Case class have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // delegates the construction to apply method
  //No need to use new for Case classes

  // 6. CCs are serializable

  //7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  //Exercises
  /*
  Expand MyList - use case classes and case objects wherever it is needed
   */

}
