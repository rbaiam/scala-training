package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  //We are not initiating an abstract class here
  //This call is creating an anonymous class , and the compiler is assigning a random name to it e.g. anon&1
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("Haaaaaahhaaa")
  }

  //What the Compiler is doing , equivalent to above
  /*
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("Haaaaaahhaaa")
    }

    val funnyAniaml = new AnonymousClasses$$anon$1()
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service ?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"I am Jim , mate !!")
  }

  //Exercises
  /*
   1. Generic trait MyPredicate[-T] : has a small  method checking if object of type T is passing a given condition , test(T) => Boolean
   2. Generic trait MyTransformer[-A,B] , : method to convert a value of type A to type transform(A) => B
   3. MyList:
       - map(transformer) => MyList
       - filter(predicate) => MyList
       - flatMap(transformer from A to MyList B) => MyList[B]

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      [1,2,3].map(n*2) = [2,4,6]
      [1,2,3,4].filer(n%2) = [2,4]
      [1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
   */

}
