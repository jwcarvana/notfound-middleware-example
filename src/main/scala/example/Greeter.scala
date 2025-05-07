package example

import zio._
import zio.macros.accessible

@accessible
trait Greeter {
  def greet(name: String): String
}

object GreeterImpl {
  val layer: ZLayer[Any, Nothing, Greeter] =
    ZLayer.succeed(GreeterImpl())
}
case class GreeterImpl() extends Greeter {
  override def greet(name: String): String =
    s"Hello, $name!"
}
