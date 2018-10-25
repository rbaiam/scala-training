package lectures.part2oop

object InheritanceAndTraits extends App {

  class Animal {
    val creatureType = "wild"

    def eat = println("nomnom")
  }

  // Single class inheritance
  final class Cat extends Animal {
    def crunch = {
      super.eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // or simply thanks to auxiliary constructor Person(name)

  //Overriding
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType = "domestic" , directly overriden via constructor

    override def eat = println("crunch, baby, crunch")
  }

  val dog = new Dog("homeless")
  dog.eat
  println(dog.creatureType)

  // Type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  //overRIDING Vs overLOADING

  //super

  // preventing overrides
  //1- use keyword final : e.g. final def eat = println("nomnom")
  //2- user keyword final on the entire class : e.g. final class Animal {
  //3- seal the class = accept extend classes in THIS FILE only !, and prevent extension in other files : e.g. sealed class Animal {
}
