# TechEazyProject README

## Project Overview

This Java demo project for TechEazy. It includes the Backend application.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) version 21 or higher
- Maven for dependency management
- An IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code (prefered)

## Database Schema (Er Diagram)

![TechEasy ER Diagram](TechEasy%20ER%20Diagram.jpg)

## Database Schema

This section provides an overview of the database schema used in the project.

### Tables

#### 1. `Student`

| Field     | Type         | Description                 |
| --------- | ------------ | --------------------------- |
| `id`      | INT          | Primary Key, Auto Increment |
| `name`    | VARCHAR(255) | Student's name              |
| `address` | VARCHAR(255) | Student's Address           |

#### 2. `Enrollment`

| Field        | Type         | Description                                       |
| ------------ | ------------ | ------------------------------------------------- |
| `id`         | INT          | Primary Key, Auto Increment                       |
| `stuent_id`  | VARCHAR(255) | StudentId Foreign Key referencing student_id(FK)  |
| `subject_id` | VARCHAR(255) | SubjectId (FK) Foreign Key referencing subject_id |

#### 3. `Subject`

| Field  | Type         | Description                 |
| ------ | ------------ | --------------------------- |
| `id`   | INT          | Primary Key, Auto Increment |
| `name` | VARCHAR(255) | Subject's name              |

### Relationships

- A `student` can have multiple `subject`.
- A `subject` can have only one `Student`.

## Setup

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

```sh
git clone https://github.com/asjad-samdani/TechEazyProject.git
```

### 2. Open the Project

Open the project in your preferred IDE. If you're using an IDE that supports Maven or Gradle, it should automatically detect the build file and import the project.

### 3. Build the Project

```sh
mvn clean install
```

### 4. Configure Database

Settings in the application.properties file located in the src/main/resources directory.

```spring.application.name=backend
spring.datasource.url=jdbc:sqlite:techeazy.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update

```

### 5. Run the Project

### Using IDE

- Locate the main class containing the public static void main(String[] args) method.

- Right-click on the file and select Run.
  Using Command Line
  Navigate to the project directory and run the following command:

### Using Command Line

```
mvn spring-boot:run
```

### 6. API Endpoints

## List of Api EndPoint

### For Student

- `GET -api/student` - Retrieves All Student

- `POST -/api/student` - Creates Student

```sh
curl -X GET http://localhost:8080/api/student
```

```sh
curl -X POST http://localhost:8080/api/student
```

### For Subject

- ` GET -/api/subject` - Retrieves All Subject

- `POST -/api/subject`- Creates subject

```sh
curl -X GET http://localhost:8080/api/subject
```

```sh
curl -X POST http://localhost:8080/api/subject
```

### Login Auth

- GET/api/auth/login

```sh
curl -X GET http://localhost:8080/api/auth/login
```
