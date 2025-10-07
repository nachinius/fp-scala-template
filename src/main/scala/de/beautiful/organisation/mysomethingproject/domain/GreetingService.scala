package de.beautiful.organisation.mysomethingproject.domain

import cats.effect.Sync
import cats.syntax.all._
import org.typelevel.log4cats.Logger

trait GreetingService[F[_]] {
  def greet(name: String): F[String]
}

object GreetingService {
  
  def impl[F[_]: Sync](logger: Logger[F]): GreetingService[F] = 
    new GreetingService[F] {
      override def greet(name: String): F[String] = {
        for {
          _        <- logger.debug(s"Creating greeting for: $name")
          greeting <- Sync[F].pure(s"Hello, $name! Welcome to our functional Scala application.")
          _        <- logger.debug(s"Generated greeting: $greeting")
        } yield greeting
      }
    }
}
