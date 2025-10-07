# Functional Scala Template

A modern, production-ready Scala template using functional programming principles with Cats Effect 3, Http4s, and other industry-standard libraries.

## Features

- **Scala 2.13.12** with modern functional programming libraries
- **Cats Effect 3** for pure functional programming and resource management
- **Http4s 0.23** with Ember server for HTTP services
- **PureConfig** for type-safe configuration management
- **Log4Cats** for structured logging
- **Circe** for JSON handling
- **ScalaTest** with Cats Effect testing support
- **Scalafmt** for consistent code formatting

## Architecture

The project follows a clean, modular architecture:

```
src/main/scala/
├── config/          # Configuration management
├── domain/          # Business logic and services
├── http/            # HTTP routes and handlers
├── Main.scala       # Application entry point
└── Server.scala     # HTTP server setup
```

### Key Components

- **AppConfig**: Type-safe configuration using PureConfig
- **GreetingService**: Domain service with proper dependency injection
- **HealthRoutes**: Health check endpoints
- **GreetingRoutes**: Business logic endpoints
- **Server**: HTTP server with middleware (CORS, logging)

## Getting Started

### Prerequisites

- Java 11 or higher
- SBT 1.9.7

### Running the Application

```bash
sbt run
```

The server will start on `http://localhost:8080` by default.

### Available Endpoints

- `GET /health` - Health check endpoint
- `GET /hello` - Greet anonymous user
- `GET /hello/{name}` - Greet specific user

### Configuration

Configuration is managed through `src/main/resources/application.conf`:

```hocon
server {
  host = "localhost"
  host = ${?HTTP_HOST}
  port = 8080
  port = ${?HTTP_PORT}
}
```

Environment variables can override default values.

### Running Tests

```bash
sbt test
```

### Code Formatting

```bash
sbt scalafmt
```

## Development

### Project Structure

- **Domain Layer**: Pure business logic without external dependencies
- **HTTP Layer**: Web-specific concerns (routes, serialization)
- **Configuration**: Type-safe configuration management
- **Main**: Application wiring and startup

### Best Practices

- Use `Resource` for managing lifecycle of external resources
- Implement services as traits with concrete implementations
- Use explicit dependency injection rather than implicits
- Separate pure business logic from HTTP concerns
- Use structured logging with context

## Dependencies

Key libraries and their versions:

- Cats Effect: 3.5.2
- Http4s: 0.23.24
- Circe: 0.14.6
- Log4Cats: 2.6.0
- PureConfig: 0.17.4

See `project/Dependencies.scala` for the complete list.

## License

This template is provided as-is for educational and development purposes.
