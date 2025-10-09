package de.beautiful.organisation.mysomethingproject.http

import cats.effect.Sync
import cats.syntax.all._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.Logger
import de.beautiful.organisation.mysomethingproject.domain.GreetingService

class GreetingRoutes[F[_]: Sync](
    greetingService: GreetingService[F],
    logger: Logger[F]
) extends Http4sDsl[F] {

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "hello" / name =>
      for {
        _        <- logger.info(s"Received greeting request for: $name")
        greeting <- greetingService.greet(name)
        response <- Ok(greeting)
      } yield response

    case GET -> Root / "hello" =>
      for {
        _        <- logger.info("Received greeting request without name")
        greeting <- greetingService.greet("Anonymous")
        response <- Ok(greeting)
      } yield response
  }
}

object GreetingRoutes {
  def apply[F[_]: Sync](
      greetingService: GreetingService[F],
      logger: Logger[F]
  ): GreetingRoutes[F] =
    new GreetingRoutes[F](greetingService, logger)
}
