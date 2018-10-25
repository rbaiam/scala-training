package lectures.part2oop

object AbstractDataType extends App {

  //abstract

  abstract class Animal {
    val creatureType: String

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Wild"

    def eat: Unit = println("crunch, crunch")
  }

  //traits (interface)

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnonom")

    override def eat(animal: Animal): Unit = println(s"I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  //abstract and traits class can have abstract or non abstract members
  //1- traits do not have constructor parameters
  //2- You can only extend one class, but you can inherit multiple traits
  //3- traits are behaviors, abstract class describes a type of thing


  // SCALA TYPE HIERARCHY
  // scala.any => scala.anyRef (java.lang.Object) =>
}
