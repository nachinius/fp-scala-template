package de.beautiful.organisation.mysomethingproject.http

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import org.http4s._
import org.http4s.implicits._
import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AsyncFreeSpec
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import de.beautiful.organisation.mysomethingproject.domain.GreetingService

class GreetingRoutesSpec extends AsyncFreeSpec with AsyncIOSpec with Matchers {

  implicit val logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  "GreetingRoutes" - {
    val greetingService = GreetingService.impl[IO](logger)
    val routes = GreetingRoutes[IO](greetingService, logger).routes

    "GET /hello/{name}" - {
      "should return a greeting for a specific name" in {
        val request = Request[IO](Method.GET, uri"/hello/Alice")
        
        routes.orNotFound.run(request).flatMap { response =>
          response.status shouldBe Status.Ok
          response.as[String].asserting { body =>
            body should include("Alice")
            body should include("Hello")
          }
        }
      }
    }

    "GET /hello" - {
      "should return a greeting for anonymous user" in {
        val request = Request[IO](Method.GET, uri"/hello")
        
        routes.orNotFound.run(request).flatMap { response =>
          response.status shouldBe Status.Ok
          response.as[String].asserting { body =>
            body should include("Anonymous")
            body should include("Hello")
          }
        }
      }
    }
  }
}
