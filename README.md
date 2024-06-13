
# SPRING REST API ![Spring Logo](https://spring.io/img/favicon.ico)


## Description

This project is a REST API built with Spring Boot. It is designed to handle various CRUD operations for managing entities such as doctors, patients, and appointments. The project also includes security configurations and uses JWT for authentication.

## Requirements

- Java 17 or higher
- Maven 3.6.3 or higher
- PostgreSQL (or your preferred SQL database)

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/SPRING-REST-API.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd SPRING-REST-API
   ```
3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

## Configuration

1. **Database Configuration:**
   - Update the `src/main/resources/application.properties` file with your database configuration:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

2. **JWT Configuration:**
   - You can configure JWT settings in the `application.properties` file as well:
     ```properties
     jwt.secret=your_jwt_secret
     jwt.expiration=3600
     ```

## Usage

1. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
2. **Access the API:**
   - The API will be available at `http://localhost:8080`.

## API Documentation

- The API documentation is generated using SpringDoc OpenAPI. Once the application is running, you can access the API documentation at:
  ```
  http://localhost:8080/swagger-ui.html
  ```

## Testing

- Run the tests using Maven:
  ```bash
  ./mvnw test
  ```
