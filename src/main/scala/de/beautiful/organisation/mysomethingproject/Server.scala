package de.beautiful.organisation.mysomethingproject

import cats.effect._
import cats.syntax.all._
import org.http4s.implicits._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.middleware.{CORS, Logger => HttpLogger}
import org.typelevel.log4cats.Logger
import com.comcast.ip4s._
import de.beautiful.organisation.mysomethingproject.config.AppConfig
import de.beautiful.organisation.mysomethingproject.domain.GreetingService
import de.beautiful.organisation.mysomethingproject.http.{GreetingRoutes, HealthRoutes}

object Server {

  def run(config: AppConfig, logger: Logger[IO]): IO[Unit] = {

    // Create services
    val greetingService = GreetingService.impl[IO](logger)

    // Create route handlers
    val healthRoutes   = HealthRoutes[IO](logger)
    val greetingRoutes = GreetingRoutes[IO](greetingService, logger)

    // Combine all routes
    val allRoutes = healthRoutes.routes <+> greetingRoutes.routes
    val httpApp   = allRoutes.orNotFound

    // Add CORS and HTTP logging middleware
    val finalHttpApp = CORS.policy.withAllowOriginAll(
      HttpLogger.httpApp(logHeaders = true, logBody = false)(httpApp)
    )

    val server = EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString(config.server.host).getOrElse(host"localhost"))
      .withPort(Port.fromInt(config.server.port).getOrElse(port"8080"))
      .withHttpApp(finalHttpApp)
      .build

    server.use { _ =>
      logger.info(s"Server started at http://${config.server.host}:${config.server.port}") *>
        logger.info("Available endpoints:") *>
        logger.info("  GET /health - Health check") *>
        logger.info("  GET /hello - Greet anonymous user") *>
        logger.info("  GET /hello/{name} - Greet specific user") *>
        IO.never
    }
  }
}
