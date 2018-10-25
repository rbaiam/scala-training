package lectures.basicspart1

/*
* Default and Named arguments
*/
object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10, 1)
  val fact11 = trFact(11)

  def savePicture(format: String = "jpeg", width: Int = 750, height: Int): Unit = println("saving picture")

  //  savePicture(800, 800) // compilation error (leading args should be explicit)
  // How to make it work
  //1. pass in every leading argument savePicture("jpeg", 750, 800)
  // OR 2. Name the arguments !!! e.g.
  savePicture(height = 800)
  savePicture(height = 800, format = "gif", width = 600) // order of args not respected, but it works fine if we name these args !!


}
