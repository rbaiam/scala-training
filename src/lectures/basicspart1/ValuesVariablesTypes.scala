package lectures.basicspart1

/*
* Vals , Vars and Types
*/
object ValuesVariablesTypes extends App {

  val x: Int = 2
  println(x)
  // VALs are IMMUTABLE
  // x = 3 wont work
  val y = 2
  // val x = 2 (type is optional, compiler can infer types)

  // Semi columns are only needed to separate expressions in same (bad code style though)
  val aSttring: String = "a";
  val bString: String = "b"

  // Basic scala types
  val aString: String = "a"
  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4125
  val aLong: Long = 412541254125L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  // Variables

  var aVariable: Int = 4
  aVariable = 5 // side effects (to be discussed , danger of side effects)
}
