import sbt._

object Dependencies {

  val zioVersion = "2.0.20"
  val zioHttpVersion = "3.0.0-RC4"
  val tapirVersion = "1.9.8"

  val zioHttp     = "dev.zio" %% "zio-http"     % zioHttpVersion
  val tapir = "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion

  val zioTest     = "dev.zio" %% "zio-test"     % zioVersion % Test
  val zioTestSBT = "dev.zio" %% "zio-test-sbt" % zioVersion % Test
  val zioTestMagnolia = "dev.zio" %% "zio-test-magnolia" % zioVersion % Test
}
