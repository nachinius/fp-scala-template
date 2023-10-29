package de.beautiful.organisation.mysomethingproject

import cats.effect._
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Main extends IOApp {

  implicit val logger = Slf4jLogger.getLogger[IO]

  override def run(args: List[String]): IO[ExitCode] = {
     for {
      _     <- IO(println(s"Hello"))
      _     <- logger.info("Said hello")
    } yield ExitCode.Success
  }

}