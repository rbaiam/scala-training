package excercises

import java.util.NoSuchElementException

import scala.annotation.tailrec

/*
Implement your own list
head = first element of the list
tail = remainder of the list
isEmpty = is this list is empty
add(int) => new list with this element added
toString => a string representation of the list
 */

/*
 * MyList abstract Class
 */
abstract class MyList[+A] {

  def head: A

  def tailE: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](i: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B >: A](transformer: MyTransformer[A, B]): MyList[B]

  //filter(predicate) => MyList
  def filter(myPredicate: MyPredicate[A]): MyList[A]

  //flatMap(transformer from A to MyList B) => MyList[B]
  def flatMap[B >: A](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  //Better solution
  def +++[B >: A](list: MyList[B]): MyList[B]

  def reverse(): MyList[A]
}

/*
 * Empty List Class
 */
// Adding just the case keyword will prevent use from writing adhoc equals method for such collection
case object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tailE: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  def map[B >: Nothing](transformer: MyTransformer[Nothing, B]) = Empty

  def filter(myPredicate: MyPredicate[Nothing]) = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def +++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def flatMap[B >: Nothing](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def reverse(): MyList[Nothing] = Empty
}

/*
 * Cons List Class
 */

// Adding just the case keyword will prevent use from writing adhoc equals method for such collection
case class Cons[+A](h: A, t: MyList[A], te: A) extends MyList[A] {

  //New constructor
  def this(h: A, t: MyList[A]) = this(h, t, if (t.isEmpty) h else t.tailE)

  def head: A = h

  def tail: MyList[A] = t

  def tailE = te

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + head
    else head + "" + t.printElements


  //WOW reverse recursive method !!
  override def reverse(): MyList[A] = {

    @tailrec
    def reverseHelper(origin: MyList[A], accu: MyList[A]): MyList[A] = {
      if (origin.isEmpty) accu
      else reverseHelper(origin.tail, new Cons(origin.head, accu))
    }

    reverseHelper(this, Empty)
  }

  override def map[B >: A](transformer: MyTransformer[A, B]): MyList[B] = {

    //[1,2,3].map(n*2) = [2,4,6]
    //    if (t.isEmpty) new Cons(transformer.transform(h), t)
    new Cons(transformer.transform(h), t.map(transformer))
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = {
    if (list.isEmpty) this
    else {
      val temp = add(list.head)
      temp.++(list.tail)
    }
  }


  //FAR BETTER SOLUTION !!!
  override def +++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t +++ list)

  override def filter(myPredicate: MyPredicate[A]): MyList[A] = {
    //    if (t.isEmpty && myPredicate.test(h)) new Cons(h, t) Not neede t.filter covered already by Empty.filter
    if (myPredicate.test(h)) new Cons(h, t.filter(myPredicate))
    else t.filter(myPredicate)
  }

  //  override def flatMap[B >: A](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
  //    //[1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
  //    //[1,2,3].flatMap(n => [n,n+1,n+2] => [1,2,3, 2,3,4 3,4,5]
  //    val transform = transformer.transform(h) // [n,n+1,n+2]
  //    t.flatMap(transformer).++(if (!t.isEmpty) transform.reverse() else transform)
  //  }

  //FAR BETTER Solution
  def flatMap[B >: A](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    //[1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
    //[1,2,3].flatMap(n => [n,n+1,n+2] => [1,2,3, 2,3,4 3,4,5]
    transformer.transform(h) +++ t.flatMap(transformer)
  }


}


// Day 2

/*
 * My Predicate Interface
 */
trait MyPredicate[-T] {
  def test(element: T): Boolean
}

/*
 * MyPredicate Implementations
 */
class EvenPredicate extends MyPredicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}

class LongStringPredicate extends MyPredicate[String] {
  override def test(element: String): Boolean = {
    element.length > 3 && !element.startsWith("!")
  }
}

/*
 * My Transformer Interface
 */
trait MyTransformer[-A, B] {
  def transform(element: A): B
}

/*
 * My Transformer Implementations
 */
class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(element: String): Int = element.toInt
}

class MultipleNTransformer(n: Int) extends MyTransformer[Int, Int] {
  override def transform(element: Int): Int = element * n
}

class MyIncrementalTransformer extends MyTransformer[Int, MyList[Int]] {
  override def transform(element: Int): MyList[Int] = {
    val list: MyList[Int] = new Cons(element, new Cons(element + 1, Empty))
    list
  }
}

/*
 * Main application to test the different implementations
 */

object ListTest extends App {
  val list: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  val clonedList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(s"Thanks to the Powerful CASE keyword ${list == clonedList}")

  println(list.toString)

  val listOfStrs: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfStrs)


  val sameEvenPredicate = new Function1[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 % 2 == 0
  }

  val sameMultipleNTransformer = new Function2[Int, Int, Int] {
    override def apply(element: Int, n: Int): Int = element * n
  }

  println(s"Function predicate ${sameEvenPredicate(2)}")
  println(s"Function transformer ${sameMultipleNTransformer(2, 3)}")

  // Day 2 tests
  val list2 = list.map(new MultipleNTransformer(2))
  println(list2.toString)


  val list3: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  println(list3.filter(new EvenPredicate).toString)

  val list4: MyList[Int] = new Cons(5, new Cons(6, new Cons(7, new Cons(8, Empty))))

  println(s"Reverse ${list4.reverse()}")
  println(s"Concatination ${list4.++(list3.reverse())}")
  println(s"Concatination2 ${list3.+++(list4)}")


  // User Anonymous class , equivalent of above call
  println(list3.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  //[1,2,3].flatMap(n => [n,n+1] => [1,2, 2,3 3,4]
  println(s"Flat map result ${list.flatMap(new MyIncrementalTransformer)}")

  println(s"Flat map result2 ${
    list.flatMap(new MyTransformer[Int, MyList[Int]] {
      override def transform(element: Int): MyList[Int] = {
        val list: MyList[Int] = new Cons(element, new Cons(element + 1, new Cons(element + 2, Empty)))
        list
      }
    })
  }")


  //Anonymous call to MyTransformer
  println(list.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element + 100
  }))
}
