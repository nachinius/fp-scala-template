package de.beautiful.organisation.mysomethingproject.config

import pureconfig._
import pureconfig.generic.auto._

final case class ServerConfig(
  host: String,
  port: Int
)

final case class AppConfig(
  server: ServerConfig
)

object AppConfig {
  def load: AppConfig = ConfigSource.default.loadOrThrow[AppConfig]
}
