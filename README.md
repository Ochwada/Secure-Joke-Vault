# 🤣🔐 Secure Joke Vault

Secure Joke Vault is a Spring Boot web application that allows users to store, manage, and access their favorite 
jokes—safely and securely. It integrates user authentication, validation, and MongoDB for persistent storage.


## Features

- JWT-based Authentication and Authorization (Spring Security)
- RESTful API (Spring Web)
- Joke Validation (Javax Validation API)
- MongoDB Integration (Spring Data MongoDB)
- Developer Experience (Spring Boot DevTools, Lombok)
- Configuration Metadata Support (Spring Configuration Processor)


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
├── .gitignore                       # Specifies intentionally untracked files to ignore
├── .env                             # Environment variables for JWT, DB, etc.
├── pom.xml                          # Maven build configuration
├── README.md                        # Project overview and instructions

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ochwada/secure_joke_vault/
│   │   │       ├── alias/                  # Custom UserDetails implementation
│   │   │       │   └── SecurityUser.java
│   │   │       ├── config/                 # Configuration classes (e.g., RestTemplate)
│   │   │       │   └── RestTemplateConfig.java
│   │   │       ├── controller/             # REST API endpoints
│   │   │       │   └── [Controllers go here]
│   │   │       ├── model/                  # Data models (Joke, Role, User)
│   │   │       │   ├── Joke.java
│   │   │       │   ├── Role.java
│   │   │       │   └── User.java
│   │   │       ├── repository/             # Repositories for data access
│   │   │       │   ├── JokeRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       ├── security/               # JWTFilter, JWTUtil, and security config
│   │   │       │   ├── JWTFilter.java
│   │   │       │   └── JWTUtil.java
│   │   │       ├── service/                # Business logic and services
│   │   │       │   ├── JokeService.java
│   │   │       │   └── UserDetailsServiceImpl.java
│   │   │       └── SecureJokeVaultApplication.java  # Main Spring Boot application
│   │
│   │   └── resources/
│   │       ├── application.properties       # Spring Boot application settings
│   │       ├── static/                      # Static web resources (if any)
│   │       └── templates/                   # Thymeleaf templates (if used)
│
│   └── test/
│       └── java/
│           └── com/ochwada/secure_joke_vault/
│               └── [Test classes go here]

├── target/                          # Compiled output and build artifacts (ignored in VCS)
│               └── SecureJokeVaultApplicationTests.java  # Unit and integration tests

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
| GET    | `/api/jokes`       | Get all jokes (auth required) |
| POST   | `/api/jokes `      | Submit a joke (auth required) |



# Setup Instructions

1. Clone the Repository
```bash 
git clone https://github.com/yourname/secure-joke-vault.git
cd secure-joke-vault
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