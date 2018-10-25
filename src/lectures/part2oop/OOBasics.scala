package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  //  println(person.name) errror since the param name is not a field
  person.greet("Daniel")

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)

  val novel = new Novel("Great Expectation", 1861, author)

  println(novel.getAuthorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))
  val counter = new Counter()
  println(counter.inc.print)
}

//Class Primary constructor
// class parameters are not fields , you need to add the keyword val
class Person(name: String, val age: Int) {
  //body of this class
  // x is a field
  val x = 2

  //method with the class
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  //Overloading
  def greet(): Unit = println(s"Hi, I am $name")

  //Multiple constructors or overloading constructor
  // Auxiliary constructor
  def this(name: String) = this(name, 0)
}

//Exercises

/*
Novel and a Writer classes
Writer: first name, surname and year
  - method full name

  Novel: name, year of release, author
    - author age
    - isWrittenBy
    - Copy (new year of release) = new instance of novel
 */

class Writer(val name: String, val surname: String, val year: Int = 1990) {

  def getFullName() = name + " " + surname
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def getAuthorAge(): Int = java.time.Year.now.getValue - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(yearOfRelease: Int): Novel = new Novel(this.name, yearOfRelease, this.author)
}

/* Counter Class
   - receives an int value
   - method current count
   - method to increment/decrement => new counter
   - overload inc/dec to receive an amount
 */

class Counter(n: Int = 0) {
  def getCurrentCount() = n

  def inc = new Counter(n + 1) // immutability , instances are fixed
  def dec = new Counter(n - 1)

  def inc(i: Int) = new Counter(n + i)

  def print = n

}
