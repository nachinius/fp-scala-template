package de.beautiful.organisation.mysomethingproject.domain

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AsyncFreeSpec
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

class GreetingServiceSpec extends AsyncFreeSpec with AsyncIOSpec with Matchers {

  implicit val logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  "GreetingService" - {
    "should create a proper greeting" in {
      val service = GreetingService.impl[IO](logger)
      
      service.greet("Alice").asserting { greeting =>
        greeting should include("Alice")
        greeting should include("Hello")
        greeting should include("functional Scala application")
      }
    }

    "should handle empty names" in {
      val service = GreetingService.impl[IO](logger)
      
      service.greet("").asserting { greeting =>
        greeting should include("Hello")
        greeting should not be empty
      }
    }
  }
}
