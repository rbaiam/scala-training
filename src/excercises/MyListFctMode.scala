package excercises

import java.util.NoSuchElementException

import scala.annotation.tailrec

/*
Implement your own list
head = first element of the list
tail = remainder of the list
isEmptyFctMode = is this list is empty
add(int) => new list with this element added
toString => a string representation of the list
 */

/*
 * MyListFctMode abstract Class
 */
abstract class MyListFctMode[+A] {

  def head: A

  def tailE: A

  def tail: MyListFctMode[A]

  def isEmptyFctMode: Boolean

  def add[B >: A](i: B): MyListFctMode[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // High order function
  def map[B >: A](transformer: A => B): MyListFctMode[B]

  //filter(predicate) => MyListFctMode
  def filter(myPredicate: A => Boolean): MyListFctMode[A]

  //flatMap(transformer from A to MyListFctMode B) => MyListFctMode[B]
  def flatMap[B >: A](transformer: A => MyListFctMode[B]): MyListFctMode[B]

  def ++[B >: A](list: MyListFctMode[B]): MyListFctMode[B]

  //Better solution
  def +++[B >: A](list: MyListFctMode[B]): MyListFctMode[B]

  def reverse(): MyListFctMode[A]
}

/*
 * EmptyFctMode List Class
 */
// Adding just the case keyword will prevent use from writing adhoc equals method for such collection
case object EmptyFctMode extends MyListFctMode[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tailE: Nothing = throw new NoSuchElementException

  def tail: MyListFctMode[Nothing] = throw new NoSuchElementException

  def isEmptyFctMode: Boolean = true

  def add[B >: Nothing](element: B): MyListFctMode[B] = new ConsFctMode(element, EmptyFctMode)

  override def printElements: String = ""

  def map[B >: Nothing](transformer: Nothing => B) = EmptyFctMode

  def filter(myPredicate: Nothing => Boolean) = EmptyFctMode

  override def ++[B >: Nothing](list: MyListFctMode[B]): MyListFctMode[B] = list

  override def +++[B >: Nothing](list: MyListFctMode[B]): MyListFctMode[B] = list

  override def flatMap[B >: Nothing](transformer: Nothing => MyListFctMode[B]): MyListFctMode[B] = EmptyFctMode

  override def reverse(): MyListFctMode[Nothing] = EmptyFctMode
}

/*
 * ConsFctMode List Class
 */

// Adding just the case keyword will prevent use from writing adhoc equals method for such collection
case class ConsFctMode[+A](h: A, t: MyListFctMode[A], te: A) extends MyListFctMode[A] {

  //New constructor
  def this(h: A, t: MyListFctMode[A]) = this(h, t, if (t.isEmptyFctMode) h else t.tailE)

  def head: A = h

  def tail: MyListFctMode[A] = t

  def tailE = te

  def isEmptyFctMode: Boolean = false

  def add[B >: A](element: B): MyListFctMode[B] = new ConsFctMode(element, this)

  def printElements: String =
    if (t.isEmptyFctMode) "" + head
    else head + "" + t.printElements


  //WOW reverse recursive method !!
  override def reverse(): MyListFctMode[A] = {

    @tailrec
    def reverseHelper(origin: MyListFctMode[A], accu: MyListFctMode[A]): MyListFctMode[A] = {
      if (origin.isEmptyFctMode) accu
      else reverseHelper(origin.tail, new ConsFctMode(origin.head, accu))
    }

    reverseHelper(this, EmptyFctMode)
  }

  override def map[B >: A](transformer: A => B): MyListFctMode[B] = {

    //[1,2,3].map(n*2) = [2,4,6]
    //    if (t.isEmptyFctMode) new ConsFctMode(transformer.transform(h), t)
    new ConsFctMode(transformer(h), t.map(transformer))
  }

  override def ++[B >: A](list: MyListFctMode[B]): MyListFctMode[B] = {
    if (list.isEmptyFctMode) this
    else {
      val temp = add(list.head)
      temp.++(list.tail)
    }
  }


  //FAR BETTER SOLUTION !!!
  override def +++[B >: A](list: MyListFctMode[B]): MyListFctMode[B] = new ConsFctMode(h, t +++ list)

  override def filter(myPredicate: A => Boolean): MyListFctMode[A] = {
    //    if (t.isEmptyFctMode && myPredicate.test(h)) new ConsFctMode(h, t) Not neede t.filter covered already by EmptyFctMode.filter
    if (myPredicate(h)) new ConsFctMode(h, t.filter(myPredicate))
    else t.filter(myPredicate)
  }

  //  override def flatMap[B >: A](transformer: MyTransformerFctMode[A, MyListFctMode[B]]): MyListFctMode[B] = {
  //    //[1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
  //    //[1,2,3].flatMap(n => [n,n+1,n+2] => [1,2,3, 2,3,4 3,4,5]
  //    val transform = transformer.transform(h) // [n,n+1,n+2]
  //    t.flatMap(transformer).++(if (!t.isEmptyFctMode) transform.reverse() else transform)
  //  }

  //FAR BETTER Solution
  def flatMap[B >: A](transformer: A => MyListFctMode[B]): MyListFctMode[B] = {
    //[1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
    //[1,2,3].flatMap(n => [n,n+1,n+2] => [1,2,3, 2,3,4 3,4,5]
    transformer(h) +++ t.flatMap(transformer)
  }


}

///*
// * My TransformerFctMode Implementations
// */
//class StringToIntTransformerFctMode extends MyTransformerFctMode[String, Int] {
//  override def transform(element: String): Int = element.toInt
//}
//
//class MultipleNTransformerFctMode(n: Int) extends MyTransformerFctMode[Int, Int] {
//  override def transform(element: Int): Int = element * n
//}
//
//class MyIncrementalTransformerFctMode extends MyTransformerFctMode[Int, MyListFctMode[Int]] {
//  override def transform(element: Int): MyListFctMode[Int] = {
//    val list: MyListFctMode[Int] = new ConsFctMode(element, new ConsFctMode(element + 1, EmptyFctMode))
//    list
//  }
//}

/*
 * Main application to test the different implementations
 */

object ListTestFctMode extends App {
  val list: MyListFctMode[Int] = new ConsFctMode(1, new ConsFctMode(2, new ConsFctMode(3, EmptyFctMode)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmptyFctMode)

  val clonedList = new ConsFctMode(1, new ConsFctMode(2, new ConsFctMode(3, EmptyFctMode)))
  println(s"Thanks to the Powerful CASE keyword ${list == clonedList}")

  println(list.toString)

  val listOfStrs: MyListFctMode[String] = new ConsFctMode("Hello", new ConsFctMode("Scala", EmptyFctMode))
  println(listOfStrs)


  val sameEvenPredicateFctMode = new Function1[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 % 2 == 0
  }

  val sameMultipleNTransformerFctMode = new Function2[Int, Int, Int] {
    override def apply(element: Int, n: Int): Int = element * n
  }

  println(s"Function predicate ${sameEvenPredicateFctMode(2)}")
  println(s"Function transformer ${sameMultipleNTransformerFctMode(2, 3)}")

  // Day 2 tests
  val list2 = list.map(new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 3
  })
  println(list2.toString)


  val list3: MyListFctMode[Int] = new ConsFctMode(1, new ConsFctMode(2, new ConsFctMode(3, new ConsFctMode(4, EmptyFctMode))))
  println(list3.filter(new Function1[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 % 2 == 0
  }).toString)

  val list4: MyListFctMode[Int] = new ConsFctMode(5, new ConsFctMode(6, new ConsFctMode(7, new ConsFctMode(8, EmptyFctMode))))

  println(s"Reverse ${list4.reverse()}")
  println(s"Concatination ${list4.++(list3.reverse())}")
  println(s"Concatination2 ${list3.+++(list4)}")


  //Anonymous call to MyTransformerFctMode
  println(list.map(new Function[Int, Int] {
    override def apply(element: Int): Int = element + 100
  }))
}