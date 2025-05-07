import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion      := "2.13.16"
ThisBuild / fork              := true
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies ++= List("com.github.liancheng" %% "organize-imports" % "0.6.0")
ThisBuild / scalacOptions += "-Ymacro-annotations"

def settingsApp = Seq(
  name := "NotFound Middleware Example",
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  libraryDependencies ++= Seq(
    zioHttp,
    zioMacros,
    tapir,
    tapirMetrics,
    zioTest,
    zioTestSBT,
    zioTestMagnolia
  ),
)

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(settingsApp)
