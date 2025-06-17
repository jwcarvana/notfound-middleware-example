import sbt._

object Dependencies {

  object Versions {
    val zio           = "2.1.17"
    val zioHttp       = "3.2.0"
    val zioTelemetry  = "3.1.5"
    val tapir         = "1.11.3"
    val opentelemetry = "1.50.0"
    val otelAgent     = "2.16.0"
    val otelSemconv   = "1.34.0"
  }

  val zio: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio"               % Versions.zio,
    "dev.zio" %% "zio-test"          % Versions.zio % Test,
    "dev.zio" %% "zio-test-sbt"      % Versions.zio % Test,
    "dev.zio" %% "zio-test-magnolia" % Versions.zio % Test,
  )

  val http: Seq[ModuleID] = Seq(
    "dev.zio"                     %% "zio-http"              % Versions.zioHttp,
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % Versions.tapir,
  )

  val otel: Seq[ModuleID] = Seq(
    "dev.zio"                         %% "zio-opentelemetry"                         % Versions.zioTelemetry,
    "io.opentelemetry.instrumentation" % "opentelemetry-instrumentation-annotations" % Versions.otelAgent,
    "io.opentelemetry"                 % "opentelemetry-sdk"                         % Versions.opentelemetry,
    "io.opentelemetry"                 % "opentelemetry-exporter-otlp"               % Versions.opentelemetry,
    "io.opentelemetry.semconv"         % "opentelemetry-semconv"                     % Versions.otelSemconv,
    "io.opentelemetry"                 % "opentelemetry-exporter-otlp"               % Versions.opentelemetry % Runtime,
    "io.opentelemetry"                 % "opentelemetry-sdk-extension-autoconfigure" % Versions.opentelemetry % Runtime,
  )

}
