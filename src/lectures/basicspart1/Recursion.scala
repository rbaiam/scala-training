package lectures.basicspart1

import scala.annotation.tailrec

/*
* Recursion and Tail Recursion in Scala (never use loops !)
*/
object Recursion extends App {

  //@tailrec // Compilation error , when forcing the method to be tail recursion with this annotation
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of ${n} - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1) // this implementation keeps the intermediate value in a seperate stack (costly on memory)
      println(s"Computed factorial of ${n}")
      result
    }
  }

  println(factorial(10))
  //  println(factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // no compilation error , when forcing the method to be tail recursion with this annotation
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, accumulator) // TAIL RECURSION , same stack =  use recursive call as the last expression
    }

    factHelper(n, 1)
  }

  println(s"Another factorial ${anotherFactorial(50000)}")


  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  // Exercises
  //1. def that concatenates a string n times using tail recursion
  def tailConcatenateStrings(str: String, n: Int): String = {
    @tailrec
    def intermConcatination(str2: String, accumulator: String, index: Int): String = {
      if (index <= 0) accumulator
      else intermConcatination(str2, str2 + accumulator, index - 1)
    }

    intermConcatination(str, "", n)
  }

  println(tailConcatenateStrings("hello", 4))

  //2. isPrime def using tail recursion

  def tailIsPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, accumulator: Boolean): Boolean = {
      if (!accumulator) false
      else if (t <= 1) true
      else isPrimeUntil(t - 1, (n % t) != 0 && accumulator)
    }

    isPrimeUntil(n / 2, true)
  }

  println(s"is tail prime ${tailIsPrime(37)}")

  //3. Fibonacci function, tail recursive

  def tailFibonacci(n: Int): Int = {

    @tailrec
    def fibonacciHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibonacciHelper(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fibonacciHelper(2, 1, 1)
  }

  println(s"Fibonacci ${8} = ${tailFibonacci(8)}") // 1 1 2 3 5 8 13
}
