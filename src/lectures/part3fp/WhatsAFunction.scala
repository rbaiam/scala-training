package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem : oop

  val doubler = new MyFunction[Int, Int] {
    override def execute(element: Int): Int = element * 2
  }

  println(doubler execute 2) // doubler(2) if we rename excute method and replace it by apply

  //function types = Funtion1[A,B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4) // =7

  //Syntactic sugar for Function2
  // ((Int, Int) => Int) equivalent of Function2[Int, Int, Int]
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A,B,R] ==== (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  //Exercises

  /*
  1. Define a function that takes 2 strings and concatinate them
  */

  val strConcatFunc = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(strConcatFunc("MR", "EG"))

  /*
  2. Go to MyList and transform and MyPredicate into function types
*/
  /*
  3. Define a function which takes an int and returns another function which takes an int and returns an int
      - whats the type of this function
      - how to do it
   */


  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function



// In java
trait MyFunction[A, B] {
  def execute(element: A): B
}

}
