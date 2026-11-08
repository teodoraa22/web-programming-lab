# Workshop Registration App

A Spring Boot web application for browsing workshops and registering for them.
Built as a lab exercise for the Web Programming course.

## Features

- List and search workshops by title/category and maximum price
- Add, edit and delete workshops
- Assign an instructor to each workshop
- Register for a workshop (with free-seat validation)
- Registration confirmation page with client IP and browser
- Same functionality available through servlets (`/servlet/workshops`, `/servlet/registration`)

## Tech stack

- Java 25
- Spring Boot 4.1
- Spring MVC and Thymeleaf
- Lombok
- Maven

Data is stored in memory, so it resets on every restart.

## Run

```bash
./mvnw spring-boot:run
```

Then open http://localhost:8081/

## Project structure

```
model       Workshop, Instructor, Registration
repository  in-memory repositories (data in DataHolder)
service     business logic and validation
web         controllers, servlets
templates   Thymeleaf views
```
