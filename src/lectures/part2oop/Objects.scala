package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  // HOW we do it in Scala
  // Objects cannot user params in the constructor
  object Person {
    // type + its only instance
    // **static**/class  - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // A factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  // We call this design: COMPAGNIONS design pattern (Object and class with same name to seperate instance-level and class-level functionalities

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON instance

  val mary = Person
  val john = Person

  println(mary == john) // true

  val mary2 = new Person("mary")
  val john2 = new Person("jhon")
  println(mary2 == john2) // false

  val bobbie = Person(mary2, john2)
  println(bobbie.name)


  // Scala Applications = Scala object with
  // important method def main(args: Array[String]) : Unit , to be able to run it in java JVM
}
