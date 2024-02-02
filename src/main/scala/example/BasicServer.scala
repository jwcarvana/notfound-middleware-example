package example

import zio.{Scope, ZIO, ZIOAppDefault}

import zio.http._

object BasicServer extends ZIOAppDefault {

  val app1 = Routes(
    Method.GET / "" -> handler(Response.text("index")),
  ).toHttpApp @@ Middleware.requestLogging()

  val app2 = Routes(
    Method.GET / "hello" -> handler(Response.text("hello")),
  ).toHttpApp @@ Middleware.requestLogging()

  val app3 = Routes(
    Method.GET / "goodbye" -> handler(Response.text("goodbye")),
  ).toHttpApp @@ Middleware.requestLogging()

  val run =
    Server.serve(app1 ++ app2 ++ app3).provide(Server.default).exitCode
}
