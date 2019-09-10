ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ir.h4n"
ThisBuild / organizationName := "scala3playground"

lazy val root = (project in file("."))
  .settings(
    name                                   := "scala 3 playground",
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-RC3",
    resolvers ++= Seq(Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots")),
  )
