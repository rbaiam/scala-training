package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //  println(x)
  // this will crash with NullPointerExceptions

  //1. throwing and catching exceptions


  //  val weirdValue: String = throw new NullPointerException

  //Throwable classes extends the Throwable class
  // Exceptions and errors are the major Throwable subtypes

  //2. How to catch exceptions

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("no int for you !")
    else 42
  }

  val potentialValue = try {
    //code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 5 //println("Caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // this section is optional
    // it does not influence the return type of this expression
    // use finally only for side effects like logging something
    println("Finally !!!")
  }


  //3. How to define your own exceptions
  class MyException extends Exception

  val exception = new MyException

  throw exception

  // Exercises

  /*
  1. Crash your system with an OutOfMemoryError
  2. Crash with SOError
  3. PocketCalculator
    - add(x,y)
    - subtract(x,y)
    - multiply(x,y)
    - divide(x,y)

    Throw if add(x,y) exceeds INT.MAX_VALUE
   */

}
