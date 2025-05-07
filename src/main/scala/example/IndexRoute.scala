package example

import sttp.tapir.ztapir._
import zio._

object IndexRoute extends RouteGenerator[Any] {

  val index = endpoint.get.in("").out(stringBody).zServerLogic(_ => ZIO.succeed("index"))

  def endpoints: Seq[EndpointDefinition[Any]] = {
    Seq(
      EndpointDefinition(index)
    )
  }
}
