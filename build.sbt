import Dependencies._
import sbt._

lazy val mySomethingProject = (project in file("."))
  .settings(
    name := "My Something Project",
    organization := "de.beautiful.organisation",
    version := "0.1",
    scalaVersion := "2.13.12",
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.cats,
      Libraries.catsEffect,
      Libraries.catsRetry,
      Libraries.fs2,
      Libraries.catsMeowMtl,
      Libraries.catsMeowMtlEffects,
      Libraries.circeCore,
      Libraries.circeGeneric,
      Libraries.circeParser,
      Libraries.circeRefined,
      Libraries.http4sDsl,
      Libraries.http4sServer,
      Libraries.http4sClient,
      Libraries.http4sCirce,
      Libraries.monocleCore,
      Libraries.monocleMacro,
      Libraries.log4cats,
      Libraries.logback % Runtime,
      Libraries.newtype,
      Libraries.refinedCore,
      Libraries.refinedCats,
      Libraries.tapir,
      Libraries.shapeless
    )
  )

