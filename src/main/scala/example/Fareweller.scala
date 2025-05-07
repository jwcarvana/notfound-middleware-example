package example

import zio._
import zio.macros.accessible

@accessible
trait Fareweller {
  def farewell(name: String): String
}

object FarewellerImpl {
  val layer: ZLayer[Any, Nothing, Fareweller] =
    ZLayer.succeed(FarewellerImpl())
}

case class FarewellerImpl() extends Fareweller {
  override def farewell(name: String): String =
    s"Goodbye, $name!"
}
