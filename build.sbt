import Dependencies._
import sbt._

lazy val mySomethingProject = (project in file("."))
  .settings(
    name := "My Something Project",
    organization := "com.nachinius",
    version := "0.1",
    scalaVersion := "2.13.12",
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.cats,
      Libraries.catsEffect,
      Libraries.fs2,
      Libraries.circeCore,
      Libraries.circeGeneric,
      Libraries.circeParser,
      Libraries.http4sDsl,
      Libraries.http4sServer,
      Libraries.http4sClient,
      Libraries.http4sCirce,
      Libraries.log4cats,
      Libraries.logback % Runtime,
      Libraries.pureconfig,
      // Test dependencies
      Libraries.scalaTest % Test,
      Libraries.catsEffectTesting % Test
    ),
    // Compiler options for Scala 2.13
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-language:higherKinds",
      "-language:postfixOps",
      "-feature",
      "-Xfatal-warnings",
      "-Ywarn-unused:imports"
    ),
    // Dependency resolution strategy
    libraryDependencySchemes ++= Seq(
      "org.typelevel" %% "cats-effect" % VersionScheme.EarlySemVer
    )
  )

