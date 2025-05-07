package example

import sttp.capabilities.zio.ZioStreams
import sttp.tapir.Endpoint
import sttp.tapir.ztapir._
import zio._
import zio.http.Route

case class EndpointDefinition[R](
  serverEndpoint: ZServerEndpoint[R, ZioStreams],
  timeout: Duration = 10.seconds,
  shouldDocument: Boolean = true,
  shouldLog: Boolean = true,
  shouldMetrics: Boolean = true,
  shouldTrace: Boolean = true,
) {

  def widen[R2 <: R]: EndpointDefinition[R2] =
    copy(serverEndpoint = serverEndpoint.widen[R2])

}

trait RouteGenerator[R] { self =>

  def endpoints: Seq[EndpointDefinition[R]]

  def ++[R2](other: RouteGenerator[R2]): RouteGenerator[R with R2] = {

    new RouteGenerator[R with R2] {
      override def endpoints: Seq[EndpointDefinition[R with R2]] =
        self.endpoints.map(_.widen[R with R2]) ++ other.endpoints.map(_.widen[R with R2])
    }
  }
}
