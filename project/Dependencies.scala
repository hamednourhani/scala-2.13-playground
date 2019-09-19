import sbt._

object Dependencies {

  val catsVersion       = "2.0.0"
  val catsMouseVersion  = "0.23"
  val catsMtlVersion    = "0.6.0"
  val scalaTestVersion  = "3.0.8"
  val scalaCheckVersion = "1.14.0"
  val zioVersion        = "1.0.0-RC12-1"

  val catsCore   = "org.typelevel" %% "cats-core"     % catsVersion
  val catsMouse  = "org.typelevel" % "mouse_2.13"     % catsMouseVersion
  val catsMtl    = "org.typelevel" %% "cats-mtl-core" % catsMtlVersion
  val catsEffect = "org.typelevel" %% "cats-effect"   % catsVersion

  val catsMtlLaw = "org.typelevel" %% "cats-mtl-laws"    % catsMtlVersion % Test
  val effectsLaw = "org.typelevel" %% "cats-effect-laws" % catsVersion    % Test

  val scalatest  = "org.scalactic"  %% "scalactic"  % scalaTestVersion
  val scalactic  = "org.scalatest"  %% "scalatest"  % scalaTestVersion % Test
  val scalaCheck = "org.scalacheck" %% "scalacheck" % scalaCheckVersion % Test

  val zio       = "dev.zio" %% "zio"         % zioVersion
  val zioStream = "dev.zio" %% "zio-streams" % zioVersion

}
