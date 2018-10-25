package lectures.basicspart1

/*
* Different String operations
*/
object StringsOps extends App {

  // Sama as java, since Scala runs on top of the java JVM
  val str: String = "Hello, I am learning Scala"
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // a2z
  println(str.reverse)
  println(str.take(2))

  //Scala specific operations
  // S-interpolators
  val name = "Mo"
  val greeting = s"Hello, my name $name or ${name + " + expression"}"
  println(greeting)

  //F-interpolators , format options
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  //raw-interpolator
  println(raw"this is a \n newline")
  val escaped = "this is a \n newline"
  println(raw"$escaped")


}

