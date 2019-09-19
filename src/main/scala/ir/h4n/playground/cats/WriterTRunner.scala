package ir.h4n.playground.cats

object WriterTRunner extends App{

  import cats.implicits._
  import cats.data.WriterT
  import cats.~>


  /**
  * tell
    */


  val w1: WriterT[Option, List[String], Int] = WriterT.liftF(Some(123))
  val r: WriterT[Option, List[String], Int] = w1.tell(List("a","b","c")).tell(List("d","e","f"))
  println(r)


  /**
  * ap
    */

  val w2 : WriterT[Option, String, Int] = WriterT.liftF(Some(123))
  val w3 = w2.tell("error")

  val func = WriterT.liftF[Option, String,Int => List[Int]](Some(i => List(i)))
  val func2 = func.tell("log")

  val result: WriterT[Option, String, List[Int]] = w3.ap(func2)
  println(result)

  /**
  * mapK
    */
  import cats.arrow._
  import cats.data.OptionT
  import cats.Id
  val w4 = WriterT.liftF[Option, String, Int](Some(123)).tell("log")
  def toList[A](option: Option[A]): List[A] = option.toList
  def lifted[A]= FunctionK.lift(toList _)
  val listW4: WriterT[List, String, Int] = w4.mapK(lifted)
  println(listW4)


  /**
  * flatMap
    */
  val wr1 = WriterT.liftF[Option, String, Int](Some(123)).tell("error")
  val funcf = (i:Int) =>  WriterT.liftF[Option, String, Int](Some(i * 2)).tell(i.show)
  println(wr1.flatMap(funcf))

  /**
  * foldLeft
    */

  val writer8 = WriterT.liftF[Option, String, Int](Some(123)).tell("hi")
  val result8: Int = writer8.foldLeft(456)((acc,v) => acc + v)
  println(8)

  /**
  * eval
    */

  import cats.Eval

  val writer9 = WriterT.liftF[Option, String, Int](Some(123)).tell("hi")
  val result9: Eval[Int] = writer9.foldRight(Eval.now(456))((v,c) => c.map(_ + v))
  println(result9.value)

  /**
  * traverse
    */

  val writer10 = WriterT.liftF[Option, String, Int](Some(123)).tell("hi")
  val result10 = writer10.traverse[List,Int](i => List(i))
  println(result10)



}
