import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ir.h4n"
ThisBuild / organizationName := "scala2.13playground"

lazy val root = (project in file("."))
  .settings(
    name := "scala 2.13 playground",
    libraryDependencies ++= Seq(
      catsCore,
      catsEffect,
      catsMtl,
      catsMouse
    ),
    resolvers ++= Seq(Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots")),
  )
