package example

import sttp.tapir.ztapir._

object GreeterRoute extends RouteGenerator[Greeter] {

  val hello = endpoint.get.in("hello").out(stringBody).zServerLogic(_ => Greeter.greet(""))

  override def endpoints: Seq[EndpointDefinition[Greeter]] =
    Seq(
      EndpointDefinition(hello)
    )

}
