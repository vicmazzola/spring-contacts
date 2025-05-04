# Spring Contacts - Spring Boot Practice

RESTful API project built with Spring Boot, part of my **Java Advanced** studies at **FIAP**.  
Created using [Spring Initializr](https://start.spring.io/).

📚 **Chapter:** *O REST com Spring Boot*  
🎯 **Goal:** Practice creating a RESTful API using Spring Boot and Java 17.

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.4.4
- Spring Web (Spring MVC)
- Spring Data JPA
- Spring Validation
- Spring Security
- Flyway (DB migrations)
- Oracle Database (JDBC)
- Maven

## 📝 Description

Simple API to manage contacts with full CRUD support.  
Includes birthday filtering, user authentication (JWT), and will evolve as the course progresses.

## 📝 Features

- ✅ CRUD operations for contacts
- ✅ Search by name or birthday range
- 🔄 Input validation
- 🔧 Database versioning with Flyway
- 📦 Oracle DB integration
- 🔐 Authentication and authorization (Spring Security + JWT)



## 📦 How to Run

```bash
./mvnw spring-boot:run
```

## 📌 Notes
- Flyway automatically applies migrations from `/resources/db/migration`.
- Database connection details must be configured in `application.properties`.
