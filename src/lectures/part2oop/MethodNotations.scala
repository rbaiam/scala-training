package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 1) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}" // valid method
    def +(title: String): Person = new Person(s"${name} ($title)", favoriteMovie) // ex1

    def unary_! : String = s"Awesome!!"

    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1) // ex2

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my mindset is CVC !"

    def apply(n: Int): String = s"${this.name} watched Inception $n times"

    def learns(subject: String): String = s"${this.name} learns $subject"

    def learnsScala(): String = learns("Scala")
  }

  val mary = new Person("Mary", "Inception")

  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent, infix notation works only with method with single argument
  // infix notation = operator notation (art of syntactic sugar) !!

  val tom = new Person("Tom", "Flight Club")
  println(mary hangOutWith tom) //The hangoutwith method acts like an operator
  println(mary + tom)

  println(1 + 2); // is equivalent to //
  println(1.+(2))

  //ALL operators are METHODS !!

  // Prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  //unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // Post fix notations (can be used by methods that dont have any params
  println(mary.isAlive)
  println(mary isAlive)

  // Special method "apply"
  println(mary.apply())
  println(mary()) // equivalent


  // EXERCISES
  /* 1- overload the + operator
  mary + the rockstart => new person "Mary (the rockstar)"
  2- Add an age to the Person class
  Add a unary + oeprator => new person with the age + 1
  +mary => mary with the age incrementer

  3- Add a "learns" method in the Person class => Mary learns Scala
  add a learnScla method, calls learns method with "Scala"
  User is in a post fix notation

  4- Overload the apply method
  mary.apply(2) => "Mary watched Inceptions 2 times"

   */

  println((mary + "the rockstar").name)
  println(+mary.age)
  println(mary learnsScala)
  println(mary(2))


}
