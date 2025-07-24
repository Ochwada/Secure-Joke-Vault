# 🤣🔐 Secure Joke Vault

A Spring Boot REST API for secure joke sharing with JWT-based authentication and MongoDB for persistent storage.

This app allows users to register, sign in securely, and fetch random jokes that are saved under their profile. 
It follows a layered architecture with strong separation of concerns (DTOs, Services, Controllers, Repositories) and 
supports easy deployment using Docker.




## Features

- JWT-based Authentication and Authorization (Spring Security)
- RESTful API (Spring Web)
- Joke Validation (Javax Validation API)
- MongoDB Integration (Spring Data MongoDB)
- Developer Experience (Spring Boot DevTools, Lombok)
- Configuration Metadata Support (Spring Configuration Processor)

### App Features
- JWT Authentication (Signup & Signin)
- Fetch and save jokes per authenticated user 
- Clean architecture with DTOs and Mappers 
- MongoDB for document storage 
- Password encryption using BCrypt 
- Ready for integration and unit testing 
- Docker & Docker Compose support


## Dependencies

- **Spring Web** – Build REST APIs with ease
- **Lombok** – Eliminate boilerplate code
- **Validation** – Ensure data integrity with annotations
- **Spring Security** – Handle login, roles, and secure endpoints
- **Spring Data MongoDB** – Store and retrieve jokes from a MongoDB database
- **Spring Configuration Processor** – Metadata for application configuration
- **Spring Boot DevTools** – Hot-reload during development

## 📁 Project Structure
```yaml

secure-joke-vault/
├── .env                             # Environment variables (JWT secret, MongoDB URI, etc.)
├── .gitignore                       # Specifies intentionally untracked files to ignore
├── .gitattributes                   # Git configuration for language diff settings
├── Dockerfile                       # Docker build instructions for the app
├── docker-compose.yml              # Docker Compose setup (e.g., app + MongoDB)
├── mvnw, mvnw.cmd                   # Maven Wrapper scripts for platform-independent builds
├── pom.xml                          # Maven build configuration and dependencies
├── README.md                        # Project overview, usage, and setup instructions

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ochwada/secure_joke_vault/
│   │   │       ├── alias/                     # Custom UserDetails wrapper
│   │   │       │   └── SecurityUser.java
│   │   │       ├── config/                    # Security and application configuration
│   │   │       │   ├── RestTemplateConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/                # REST API endpoints
│   │   │       │   ├── AuthController.java
│   │   │       │   └── JokeController.java
│   │   │       ├── dto/                       # Data Transfer Objects
│   │   │       │   ├── JWTResponse.java
│   │   │       │   ├── SigninRequest.java
│   │   │       │   └── SignupRequest.java
│   │   │       ├── mapper/                    # Object mappers (e.g. DTO → Entity)
│   │   │       │   └── UserMapper.java
│   │   │       ├── model/                     # Domain models / MongoDB documents
│   │   │       │   ├── Joke.java
│   │   │       │   ├── Role.java
│   │   │       │   └── User.java
│   │   │       ├── repository/                # Spring Data MongoDB repositories
│   │   │       │   ├── JokeRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       ├── security/                  # JWT filters, utils
│   │   │       │   ├── JWTFilter.java
│   │   │       │   └── JWTUtil.java
│   │   │       ├── service/                   # Business logic layer
│   │   │       │   ├── JokeService.java
│   │   │       │   └── UserDetailsServiceImpl.java
│   │   │       └── SecureJokeVaultApplication.java  # Main Spring Boot application class
│   │
│   │   └── resources/
│   │       ├── application.properties         # Spring Boot app configuration
│   │       ├── static/                        # Static files (optional)
│   │       └── templates/                     # HTML templates (optional)

│
│   └── test/
│       └── java/
│           └── com/ochwada/secure_joke_vault/
│               └── SecureJokeVaultApplicationTests.java  # Unit & integration tests

├── target/                          # Compiled build output (ignored by Git)


```

## 🔒 Authentication
- JWT-based login and registration (in progress)
- Secure endpoints by user roles 
- Store jokes per user securely


## 🛠️ Development Notes
- Use Lombok annotations for clean POJOs 
- MongoDB handles JSON-like joke objects 
- Validation prevents bad joke submissions 😄

## 📬 API Endpoints (Sample)

| Method | Endpoint           | Description                   |
|--------|--------------------|-------------------------------|
| POST   | `/api/auth/signup` | Register a new user           |
| POST   | `/api/auth/login`  | Login and get JWT token       |
| GET    | `/api/joke`        | Get all jokes (auth required) |
| POST   | `/api/joke `       | Submit a joke (auth required) |



# Setup Instructions

1. Clone the Repository
```bash 
git clone https://github.com/yourname/secure-joke-vault.git
cd secure-joke-vault

# Add environment config
cp .env.example .env

# Build and run (requires Docker)
docker-compose up --build
```

2. Configure MongoDB & JWT
```bash
spring.data.mongodb.uri=mongodb://localhost:27017/jokevault
jwt.secret=your-super-secret-key
jwt.expiration=86400000
```

3. Run the App
```bash
./mvnw spring-boot:run
```