package example

import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import sttp.tapir.ztapir._
import zio.{Scope, ZIO, ZIOAppDefault}
import zio.http.{Middleware, Server}

object BasicServer extends ZIOAppDefault {

  val index = endpoint.get.in("").out(stringBody).zServerLogic(_ => ZIO.succeed("index"))

  val app1 = ZioHttpInterpreter().toHttp(index) @@ Middleware.requestLogging()

  val hello = endpoint.get.in("hello").out(stringBody).zServerLogic(_ => ZIO.succeed("hello"))

  val app2 = ZioHttpInterpreter().toHttp(hello) @@ Middleware.requestLogging()

  val goodbye = endpoint.get.in("goodbye").out(stringBody).zServerLogic(_ => ZIO.succeed("goodbye"))

  val app3 = ZioHttpInterpreter().toHttp(goodbye) @@ Middleware.requestLogging()

  val run =
    Server
      .serve(app1 ++ app2 ++ app3)
      .provide(Server.default)
      .exitCode

}
