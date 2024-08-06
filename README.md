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

### ER Diagram

If you have an Entity-Relationship Diagram (ERD), you can include it here. If not, you can describe the relationships textually.

## Setup

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

```sh
git clone https://github.com/asjad-samdani/TechEazyProject.git
```

### 2. Open the Project

### 3. Build the Project

### 4. Configure Database (if applicable)

Settings in the application.properties file located in the src/main/resources directory.

```spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 5. Run the Project

Maven

```
mvn spring-boot:run
```

### 6. API Endpoints

List of Api EndPoint

### For Student

- GET api/student - Retrieves All Student

- POST /api/student - Creates Student

### For Subject

- GET/api/subject - Retrieves All Subject

- POST/api/subject - Creates subject

### Login Auth

- GET/api/auth/login
