package lectures.basicspart1

/*
* Expressions Vs Instructions
*/
object Expressions extends App {

  val x = 1 + 2 // We call this an expression
  println(x)

  println(2 + 3 * 4)
  // math expressions + - * / & / ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == !=  > >= < <=

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ...... side effects
  println(aVariable)

  // Instructions Vs Expressions
  // Instruction is an action you tell the Computer to DO
  // An expression has a value or type

  // IF expression
  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3 // AN IF Expression since it returns a value
  println(aConditionValue)

  // Instructions not good ... side effects
  // Imperative code like Java or python style // NEVER write this again
  // Instructions DO something (think java)
  // Expressions Evaluate the value of something
  var i = 0
  val aWhile: Unit = while (i < 10) {
    println(i)
    i += 1
  }

  // Everything in scala is an expression !

  val aWeirdValue = (aVariable = 3) // Unit === void (java)
  println(aWeirdValue)

  // side effects : println(), whiles, reassigning

  // Code Blocks
  // Code block is an expression
  // Value of the block is the value of the last expression
  // Everything we declare inside the code block stay visible only within the code block
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // val anotherValue = z + 1 cannot resolve z

  //1. difference between "hello world" vs println("hello world")
  // First is value String, second is value Unit (side effect, instruction)
  //2. whats the value of

  // Value: Boolean = true
  val someValue = {
    2 < 3
  }

  // Value : Int = 42
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
}
