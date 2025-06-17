import Dependencies._

ThisBuild / organization      := "com.example"
ThisBuild / version           := "0.0.1"
ThisBuild / scalaVersion      := "2.13.16"
ThisBuild / fork              := true
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

ThisBuild / scalafixDependencies ++= List(
  "com.github.liancheng" %% "organize-imports" % "0.6.0",
)

def settingsApp = Seq(
  name                                      := "Telemetry Agent POC",
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  javaOptions ++= Seq(
    "-Dotel.java.global-autoconfigure.enabled=true",
    "-Dotel.service.name=telemetry-agent-poc",
    "-Dotel.exporter.otlp.endpoint=http://localhost:4317",
    "-Dotel.exporter.otlp.protocol=grpc",
  ),
  javaAgents += "io.opentelemetry.javaagent" % "opentelemetry-javaagent" % "2.16.0" % Runtime,
  libraryDependencies ++= zio ++ http ++ otel,
)

lazy val root = (project in file("."))
  .enablePlugins(JavaAgent, JavaAppPackaging)
  .settings(settingsApp)
