package example

import sttp.tapir.server.interceptor.metrics.MetricsRequestInterceptor
import sttp.tapir.server.metrics.zio.ZioMetrics
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import sttp.tapir.ztapir._
import zio.{Scope, Task, ZIO, ZIOAppDefault}
import zio.http.{Middleware, Server}

object BasicServer extends ZIOAppDefault {


  val routes: RouteGenerator[Greeter with Fareweller] = IndexRoute ++ GreeterRoute ++ FarewellerRoute

  private val metrics: ZioMetrics[Task]                           = ZioMetrics.default[Task]()
  private val metricsInterceptor: MetricsRequestInterceptor[Task] = metrics.metricsInterceptor()

  val serverOptions = ZioHttpServerOptions.customiseInterceptors
    .metricsInterceptor(metricsInterceptor)
    .options

  val app = ZioHttpInterpreter[Greeter with Fareweller](serverOptions).toHttp(routes.endpoints.map(_.serverEndpoint))

  val run =
    Server.serve(app)
    .provide(
      Server.default,
      GreeterImpl.layer,
      FarewellerImpl.layer
    ).exitCode
}
