package de.beautiful.organisation.mysomethingproject

import cats.effect._
import cats.syntax.functor._
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import de.beautiful.organisation.mysomethingproject.config.AppConfig

object Main extends IOApp.Simple {

  private def createLogger[F[_]: Sync]: F[Logger[F]] = 
    Slf4jLogger.create[F].widen

  private def program: IO[Unit] = {
    for {
      logger <- createLogger[IO]
      config <- IO(AppConfig.load)
      _      <- logger.info(s"Starting application with config: $config")
      _      <- logger.info("Application started successfully")
      _      <- Server.run(config, logger)
    } yield ()
  }

  override def run: IO[Unit] = 
    program.handleErrorWith { error =>
      IO(System.err.println(s"Application failed to start: ${error.getMessage}")) *>
      IO.raiseError(error)
    }
}