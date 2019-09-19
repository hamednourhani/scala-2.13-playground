package ir.h4n.playground.cats
import cats.kernel.Semigroup

object IorRunner extends App{


  import cats.data.Ior
  import cats.implicits._

  val ior1: Ior[String, Int] = "abc".leftIor[Int]
  val ior2: Ior[String, Int] = 123.rightIor[String]
  val ior3: Ior[String, Int] = Ior.Both("abc",123)

  val rOfI1: String = ior1.fold(identity,a => a.show,(a,b) => a.combine(b.show))
  println(rOfI1)
  val rOfI2: String = ior2.fold(identity,a => s"$a",(a,b) => s"($a,$b)")
  println(rOfI2)
  val rOfI3: String = ior3.fold(identity,a => s"$a",(a,b) => s"($a,$b)")
  println(rOfI3)

}
