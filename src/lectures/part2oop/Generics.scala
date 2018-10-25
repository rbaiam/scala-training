package lectures.part2oop

object Generics extends App {

  // A generic class
  class MyList[+A] {

    // we can use now the type A
    def add[B >: A](element: B): MyList[B] = ???

    /*
    A = Cat
    B = Animal
     */
  }

  class MyMap[Key, Value]


  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  //generic methods

  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. Yes List[Cat] extends List[Animal] = COVARIANCE
  class CovarianceList[+A]

  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat]

  // animalList.add(new Dog)  ?? difficult question !
  // ANSWER: we return a list of animals add[Animal >: Cat](element: Dog) = new Mylist[Animal]

  // 2. NO = INVARIANCE
  class InvariantList[A]

  val invariantList: InvariantList[Animal] = new InvariantList[Animal] // error when : new  InvariantList[Cat]

  // 3. Hell, no ! CONTRAVARIANCE
  class ContravaraintList[-A]

  val contravaraintList: ContravaraintList[Cat] = new ContravaraintList[Animal]


  // Contravariance has more sense with this example
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]


  //Bounded types

  class Cage[A <: Animal] // class that accepted types that extend Animal class (animal subtypes)
  //    class Cage[A >: Animal] // class that accepted super types of animal

  val cage = new Cage[Dog]

  //    class Car
  //    val cage = new Cage(new Car) => error


  //Exercise expand MyList to generic
}
