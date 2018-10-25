package lectures.basicspart1

/*
* How to write proper functions in Scala
*/
object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  // A function is an expression
  println(aFunction("hello", 3))

  def aPramaterLessFunction(): Int = 42

  println(aPramaterLessFunction())
  println(aPramaterLessFunction)

  //Recursive function
  // In Scala, WHEN you need loops , USE RECURSION. THis is fundemental
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  // Return type in a normal function is optional, however mandatory in recursive functions

  println(aRepeatedFunction("hello", 3))

  // Unit function
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  // Auxiliary functions
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  // EXERCISES !
  //1. A greeting function for kids, takes in put (name , age) => "Hi my name is $name and I am $age years old"

  def greetingFunction(name: String, age: Int): String = {
    "Hi my name is " + name + " and I am " + age + " years old"
  }

  println(greetingFunction("Mohamed", 27))

  //2. Write a factorial function 1 * 2* 3 * .... n

  def factorialFunction(n: Int): Int = {
    if (n <= 0) 1
    else n * factorialFunction(n - 1)
  }

  println(factorialFunction(5))

  //3. A fibonacci function : f(1) = 1 , f (2) = 1 , f(n) = f(n-1) + f(n-2)

  def fibonacciFunction(n: Int): Int = {
    if (n <= 2) 1
    else fibonacciFunction(n - 1) + fibonacciFunction(n - 2)
  }

  println(s"Fibonacci ${fibonacciFunction(8)}")

  //4. A function that tests if a number is prime

  def isOddFunction(n: Int): Boolean = {
    (n % 2) != 0
  }

  println("is Odd ?: " + isOddFunction(21))

  //5. A function that tests if a number is prime

  def isPrimeFunction(n: Int): Boolean = {

    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else (n % t) != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println("isPrime ? : " + isPrimeFunction(37))
}
