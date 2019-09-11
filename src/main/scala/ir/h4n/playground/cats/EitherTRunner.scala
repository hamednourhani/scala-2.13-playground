package ir.h4n.playground.cats

import cats.data.EitherT
import cats.data.OptionT
import cats.implicits._

object EitherTRunner extends App {

  /**
    * foldF
    */
  val eitherTFoldF: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val resultFoldF = eitherTFoldF.foldF(string => string.split("").toList, int => List(s"$int"))
  println(resultFoldF)

  val eitherTIsLeft: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val resultIsLeft = eitherTIsLeft.isLeft
  println(resultIsLeft)

  /**
    * getOrElse
    */
  val eitherTGetOrElse: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val resultGetOrElse = eitherTGetOrElse.getOrElse(456)
  println(resultGetOrElse)

  /**
    * orElse
    */
  val eitherTOrElse: EitherT[Option, String, Int] = EitherT[Option, String, Int](Some(Right(123)))
  val resultOrElse = eitherTOrElse.orElse(EitherT[Option, Boolean, Int](Some(Right(456))))
  println(resultOrElse)

  val eitherTOrElse2: EitherT[Option, String, Int] = EitherT[Option, String, Int](Some(Left("abc")))
  val resultOrElse2 = eitherTOrElse2.orElse(EitherT[Option, Boolean, Int](Some(Left(true))))
  println(resultOrElse2)

  /**
    * recover
    */
  val eitherTRecover:  EitherT[List, String, Int]   = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val pf:              PartialFunction[String, Int] = { case "abc" => 456 }
  val resultOfRecover: EitherT[List, String, Int]   = eitherTRecover.recover(pf)
  println(resultOfRecover)

  /**
    * recoverWith
    */
  val eitherTRecoverWith: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))

  val pf2: PartialFunction[String, EitherT[List, String, Int]] = {
    case "abc" => EitherT[List, String, Int](List(Right(456)))
  }
  val resultOfRecoverWith: EitherT[List, String, Int] = eitherTRecoverWith.recoverWith(pf2)
  println(resultOfRecover)

  /**
    * rethrowT
    */
  val e1: EitherT[Option, Unit, Int] = EitherT[Option, Unit, Int](Some(Right(123)))
  val e1Result = e1.rethrowT
  println(e1Result)

  val e2: EitherT[Option, Unit, Int] = EitherT[Option, Unit, Int](Some(Left(())))
  val e2Result = e2.rethrowT
  println(e2Result)


  /**
  *  valuOr
    */

  val valueOrEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val valueOrResult = valueOrEitherT.valueOr(_.length)
  println(valueOrResult)


  /**
    *  valuOrF
    */

  val valueOrFEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val valueOrFResult = valueOrFEitherT.valueOrF(string => List(string.length))
  println(valueOrFResult)


  /**
  * forall
    */

  val forallEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val forallResult = forallEitherT.forall(_ > 100)
  println(forallResult)

  /**
  * ensure
    */

  val ensureEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val ensureResult = ensureEitherT.ensure("efg")(_ > 150)
  println(ensureResult)



  /**
    * ensureOr
    */

  val ensureOrEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val ensureOrResult = ensureOrEitherT.ensureOr(int => s"$int")(_ > 150)
  println(ensureOrResult)


  /**
  * toOption
    */

  val toOptionEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val toOptionResult: OptionT[List, Int] = toOptionEitherT.toOption
  println(toOptionResult)

  /**
  * to
    */

  val toEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val toResult: List[Option[Int]] = toEitherT.to[Option]
  println(toResult)


  /**
  * collectRight
    */
  val collectRightEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val collectRightResult: List[Int] = collectRightEitherT.collectRight
  println(collectRightResult)

  /**
  * bimap
    */

  val bimapEitherT: EitherT[List, String, Int] = EitherT[List, String, Int](List(Right(123), Left("abc")))
  val bimapResult: EitherT[List, Int, Int] = bimapEitherT.bimap(string => string.length, int => int % 100)
  println(bimapResult)






}
