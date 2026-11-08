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

```text
web-programming-lab
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .mvn
│   └── wrapper
│       └── maven-wrapper.properties
└── src
    └── main
        ├── java/mk/ukim/finki/wp/lab
        │   ├── WpApplication.java
        │   │
        │   ├── bootstrap
        │   │   └── DataHolder.java
        │   │
        │   ├── model
        │   │   ├── Workshop.java
        │   │   ├── Instructor.java
        │   │   └── Registration.java
        │   │
        │   ├── repository
        │   │   ├── WorkshopRepository.java
        │   │   ├── InstructorRepository.java
        │   │   ├── RegistrationRepository.java
        │   │   └── impl
        │   │       ├── InMemoryWorkshopRepository.java
        │   │       ├── InMemoryInstructorRepository.java
        │   │       └── InMemoryRegistrationRepository.java
        │   │
        │   ├── service
        │   │   ├── WorkshopService.java
        │   │   ├── InstructorService.java
        │   │   ├── RegistrationService.java
        │   │   └── impl
        │   │       ├── WorkshopServiceImpl.java
        │   │       ├── InstructorServiceImpl.java
        │   │       └── RegistrationServiceImpl.java
        │   │
        │   └── web
        │       ├── controller
        │       │   ├── WorkshopController.java
        │       │   └── RegistrationController.java
        │       └── servlet
        │           ├── WorkshopListServlet.java
        │           └── RegistrationServlet.java
        │
        └── resources
            ├── application.properties
            └── templates
                ├── listWorkshops.html
                ├── workshop-form.html
                ├── registrations.html
                └── registrationConfirmation.html
```

### Layers

| Layer | Responsibility |
|---|---|
| `bootstrap` | `DataHolder` initializes the in-memory data |
| `model` | Data classes (`Workshop`, `Instructor`, `Registration`) |
| `repository` | Data access, in-memory implementations |
| `service` | Business logic and validation |
| `web` | Spring MVC controllers and servlets |
| `templates` | Thymeleaf views |
