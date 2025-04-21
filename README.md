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
- Flyway (DB migrations)
- Oracle Database (JDBC)
- Maven

## 📝 Description

Simple API to manage contacts with full CRUD support.  
Includes birthday filtering and will evolve as the course progresses.

## 📝 Features

- ✅ CRUD for Contacts
- ✅ Search by name or birthday range
- 🔄 Data validation
- 🔧 DB versioning with Flyway
- 📦 Oracle DB support

## 📦 How to Run

```bash
./mvnw spring-boot:run

```

## 📌 Notes
Flyway auto-applies migrations from /resources/db/migration

Uses Oracle DB (configure in application.properties)
