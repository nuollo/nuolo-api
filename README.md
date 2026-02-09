# nuolo-api

Backend REST API for the Nuolo web app. Built with Spring Boot 3, PostgreSQL, jOOQ, and JWT authentication.

## Prerequisites

- **Java 21**
- **Maven 3.9+**
- **PostgreSQL 15+**

## Database setup

1. Create a PostgreSQL database:

```sql
CREATE DATABASE nuolo;
```

Flyway will automatically run migrations on startup. The initial migration creates the `users` table:

```sql
CREATE TABLE users (
    id         SERIAL PRIMARY KEY,
    user_id    VARCHAR(60)  NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now()
);
```

## Configuration

The app uses Spring profiles. Create your own profile file:

```
src/main/resources/application-{yourname}.properties
```

Then set the active profile in `application.properties`:

```properties
spring.profiles.active={yourname}
```

### Required properties

```properties
server.port=8081

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/nuolo
spring.datasource.username=postgres
spring.datasource.password=your_password

# JWT
app.jwt.secret=your-secret-key-must-be-at-least-256-bits-long
app.jwt.expiration-ms=86400000

# CORS
app.cors.allowed-origin=http://localhost:3000
```

| Property | Description |
|---|---|
| `server.port` | Port the API runs on |
| `spring.datasource.url` | PostgreSQL JDBC connection URL |
| `spring.datasource.username` | Database username |
| `spring.datasource.password` | Database password |
| `app.jwt.secret` | Secret key for signing JWT tokens (min 256 bits) |
| `app.jwt.expiration-ms` | Token expiration time in milliseconds (default: 86400000 = 24h) |
| `app.cors.allowed-origin` | Allowed frontend origin for CORS |

## Running the app

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

The API will start at `http://localhost:8081` (or whatever port you configured).
```

## Project structure

```
com.nuolo.api/
├── auth/           Auth feature (controller, service, request/response DTOs)
├── user/           User feature (entity, repository, response DTO, jOOQ table)
├── security/       JWT service, authentication filter, UserDetailsService
├── config/         Security and CORS configuration
├── exception/      Global exception handler, custom exceptions
└── NuoloApiApplication.java
```

## Tech stack

- **Spring Boot 3.4** — web framework
- **Spring Security** — authentication & authorization
- **jOOQ** — type-safe SQL queries
- **Flyway** — database migrations
- **PostgreSQL** — database
- **JWT (jjwt 0.12)** — stateless authentication tokens
- **Immutables** — compile-time generated value objects for entities and DTOs
- **Lombok** — boilerplate reduction
- **Jakarta Validation** — request validation (`@NotBlank`, `@Email`, `@Size`)
