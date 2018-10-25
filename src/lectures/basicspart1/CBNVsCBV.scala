package lectures.basicspart1

/*
* Call By Name Vs Call By Value
*/
object CBNVsCBV extends App {

  /*
  * Value is computed before the call
  * Same value is used everywhere
  */
  def calledByValue(x: Long): Unit = {
    println(s"by value: ${x}")
    println(s"by value: ${x}")
    //Similar to
    //    println(s"by value: ${125378977777L}")
    //    println(s"by value: ${125378977777L}")

  }

  /*
  * Expression is passed literally
  * Expression is evaluated at every use within the function definition
  */
  def calledByName(x: => Long): Unit = {
    println(s"by name: ${x}")
    println(s"by name: ${x}")
    //Similar to
    //    println(s"by name: ${System.nanoTime()}")
    //    println(s"by name: ${System.nanoTime()}")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  //  printFirst(infinite(), 45) // It will crash since it is effectively calling the infinite method
  printFirst(45, infinite()) // works fine, since the call to the infinite method is only happening when we invoke it (never used in this example)
}
