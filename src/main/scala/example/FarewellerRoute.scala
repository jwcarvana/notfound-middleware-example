package example

import sttp.tapir.ztapir._

object FarewellerRoute extends RouteGenerator[Fareweller] {

  val goodbye = endpoint.get.in("goodbye").out(stringBody).zServerLogic(_ => Fareweller.farewell(""))

  override def endpoints: Seq[EndpointDefinition[Fareweller]] =
    Seq(
      EndpointDefinition(goodbye)
    )
}
