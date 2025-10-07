package de.beautiful.organisation.mysomethingproject.http

import cats.effect.Sync
import cats.syntax.all._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.Logger
import io.circe.generic.auto._
import org.http4s.circe.CirceEntityCodec._

case class HealthStatus(status: String, timestamp: Long)

class HealthRoutes[F[_]: Sync](logger: Logger[F]) extends Http4sDsl[F] {

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "health" =>
      for {
        _         <- logger.debug("Health check requested")
        timestamp <- Sync[F].delay(System.currentTimeMillis())
        status    = HealthStatus("OK", timestamp)
        response  <- Ok(status)
      } yield response
  }
}

object HealthRoutes {
  def apply[F[_]: Sync](logger: Logger[F]): HealthRoutes[F] = 
    new HealthRoutes[F](logger)
}
