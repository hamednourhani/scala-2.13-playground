package ir.h4n.playground.cats
import cats.data.Ior
import cats.data.NonEmptyChain
import cats.data.NonEmptyList
import cats.data.Validated
import cats.data.ValidatedNel

object ValidatedRunner extends App {

  import cats.implicits._

  val v1= "error".invalid[Option[Int]]
  val v2= Some(123).valid

  val resultFoldV1: Option[Int] = v1.fold(_ => None,identity)
  println(resultFoldV1)
  val resultFoldV2: Option[Int] = v2.fold(_ => None,identity)
  println(resultFoldV2)

  /**
  * isValid
    */

  val v1IsValid= "error".invalid[Option[Int]]
  val v2IsValid= Some(123).valid

  val resultIsValidV1: Boolean = v1IsValid.isValid
  println(resultIsValidV1)
  val resultIsValidV2: Boolean = v2IsValid.isValid
  println(resultIsValidV2)

  /**
  * foreach
    */

  val foreachValidated1 = Some(123).valid
  val foreachValidated2 = "error".invalid[Option[Int]]

  val resultForeachValidted1: Unit = foreachValidated1.foreach(_.foreach(println))

  val e: Validated[Exception, Int] = new Exception("error").invalid[Int]

  /**
  * orElse
    */

   val orElsev1 = Some("error").invalid[Int]
   val orElsev2 = 123.valid[Option[String]]
   val defaultValidated = 456.valid[Option[String]]
   val resultOrElsev1: Validated[Option[String], Int] = orElsev1.orElse(defaultValidated)
  println(resultOrElsev1)
    val resultOrElsev2: Validated[Option[String], Int] = orElsev2.orElse(defaultValidated)


  /**
  * toIor
    */

  val toIorV1 = new Exception("error").invalid[Int]
  val toIorV2 = 123.valid[Exception]

  val resultToIorV1: Ior[Exception, Int] = toIorV1.toIor
  println(resultToIorV1)
  val resultToIorV2: Ior[Exception, Int] = toIorV2.toIor
  println(resultToIorV2)


  /**
  * toValidatedNel
    */

  val toValidatedNel1 = "error".invalid[Int]
  val toValidatedNel2 = 123.valid[String]

  val resultOfToValidatedNel1: ValidatedNel[String, Int] = toValidatedNel1.toValidatedNel
  println(resultOfToValidatedNel1)

  val resultOfToValidatedNel2: ValidatedNel[String, Int] = toValidatedNel2.toValidatedNel
  println(resultOfToValidatedNel2)


  /**
  * withEither
    */

  val withEither1 = "error".invalid[Int]
  val withEither2 = 123.valid[String]

  val resultOfWithEither1 = withEither1.withEither(_.bimap(List(_),Some(_)))
  println(resultOfWithEither1)

  val resultOfWithEither2 = withEither2.withEither(_.bimap(List(_),Some(_)))
  println(resultOfWithEither2)

  /**
  * ap
    */

  val apV1 = "error".invalid[Int]
  val apV2 = 123.valid[String]
  val apF: Validated[String, Int => Option[Int]] = (Option.apply[Int] _).valid[String]

  val resultApV1: Validated[String, Option[Int]] = apV1.ap(apF)
  println(resultApV1)

  val resultApV2 : Validated[String, Option[Int]] = apV2.ap(apF)
  println(resultApV2)

  /**
  * product
    */

  import cats.data.ValidatedNec

  val productV1: ValidatedNec[String, Int] = "error".invalidNec[Int]
  val productV2: ValidatedNec[String, Int] = "error2".invalidNec[Int]
  val productV3: ValidatedNec[String, Int] = 123.validNec[String]
  val productV4: ValidatedNec[String, Int] = 456.validNec[String]

  val resultOfProductV1: ValidatedNec[String, (Int, Int)] = productV1.product(productV2)
  println(resultOfProductV1)

  val resultOfProductV2: ValidatedNec[String, (Int, Int)] = productV3.product(productV4)
  println(resultOfProductV2)

  /**
  * traverse
    */

  val traverseV1 = "error".invalid[Int]
  val traverseV2 = 123.valid[String]

  val resultOfTraverseV1: Option[Validated[String, Int]] = traverseV1.traverse(Option.apply[Int])
  println(resultOfTraverseV1)

  val resultOfTraverseV2: Option[Validated[String, Int]] = traverseV2.traverse(Option.apply[Int])
  println(resultOfTraverseV2)

  /**
  * foldRight
    */

  import cats.Eval

  val foldRightV1 = "error".invalid[Int]
  val foldRightV2 = 123.valid[String]

  val resultOfFoldRightV1: Eval[Int] = foldRightV1.foldRight(Eval.now(456))((i,e) => e.map(_ + i))
  println(resultOfFoldRightV1)

  val resultOfFoldRightV2: Eval[Int] = foldRightV2.foldRight(Eval.now(456))((i,e) => e.map(j => j + i))
  println(resultOfFoldRightV2.value)

  val f: Int => Validated[String, List[Int]] = List(_).valid[String]

  /**
  * combine
    */

  val combineV1: ValidatedNel[String, List[Int]] = "error".invalidNel[List[Int]]
  val combineV2: ValidatedNel[String, List[Int]] = "error2".invalidNel[List[Int]]
  val combineV3: ValidatedNel[String, List[Int]] = List(123).validNel[String]
  val combineV4: ValidatedNel[String, List[Int]] = List(456).validNel[String]

  val resCombine1: Validated[NonEmptyList[String], List[Int]] = combineV1.combine(combineV2)
  println(resCombine1)
  val resCombine2: Validated[NonEmptyList[String], List[Int]] = combineV2.combine(combineV3)
  println(resCombine2)
  val resCombine3: Validated[NonEmptyList[String], List[Int]] = combineV3.combine(combineV4)
  println(resCombine3)

  /**
  * merge
    */

  val mergeV1 = Seq("error").invalid[List[String]]
  val mergeV2= List("Ok").valid[Seq[String]]

  val resultOfMergeV1 = mergeV1.merge

}
