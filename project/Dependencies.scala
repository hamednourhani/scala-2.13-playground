import sbt._

object Dependencies {

  val catsVersion      = "2.0.0"
  val catsMouseVersion = "0.23"
  val catsMtlVersion   = "0.6.0"

  val catsCore   = "org.typelevel" %% "cats-core"     % catsVersion
  val catsMouse  = "org.typelevel" % "mouse_2.13"     % catsMouseVersion
  val catsMtl    = "org.typelevel" %% "cats-mtl-core" % catsMtlVersion
  val catsEffect = "org.typelevel" %% "cats-effect"   % catsVersion

  val catsMtlLaw = "org.typelevel" %% "cats-mtl-laws"    % catsMtlVersion % Test
  val effectsLaw = "org.typelevel" %% "cats-effect-laws" % catsVersion    % Test

}
