# TechEazyProject README

## Project Overview

This Java demo project for TechEazy. It includes the Backend application.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) version 21 or higher
- Maven for dependency management
- An IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code (prefered)

## Database Schema (Er Diagram)

![TechEasy ER Diagram](TechEasyERDiagram.png)

## Database Schema

This section provides an overview of the database schema used in the project.

### Tables

#### 1. `User (Student)`

| Field      | Type         | Description                 |
| ---------- | ------------ | --------------------------- |
| `id`       | INT          | Primary Key, Auto Increment |
| `name`     | VARCHAR(255) | User's name                 |
| `address`  | VARCHAR(255) | User's Address              |
| `email`    | VARCHAR(255) | User's Email                |
| `password` | VARCHAR(255) | User's Password             |

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

### 4. Run the Project

### Using IDE

- Locate the main class containing the public static void main(String[] args) method.

- Right-click on the file and select Run.
  Using Command Line
  Navigate to the project directory and run the following command:

### Using Command Line

```
mvn spring-boot:run
```

## API Endpoints

### 1. For User/Student (Requires Authentication)

- `GET  - /api/student` - Retrieves Students

```sh
curl -X GET http://localhost:8080/api/student \
 --header 'Authorization: Bearer 74c54a3f-a867-4b49-b3c8-f813a4eb3fb3'
```

> Note : Admin can see all the users data but a Student can see only his data

- `POST -/api/student` - Creates Student

```sh
curl  -X POST \
  'localhost:8080/api/student' \
  --header 'Accept: */*' \
  --header 'Authorization: Bearer 74c54a3f-a867-4b49-b3c8-f813a4eb3fb3' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "John Doe",
  "address": "Pune",
  "email": "john@gmail.com",
  "password": "pass123",
  "role": "student",
  "subject_ids":[1,2]
}'
```

> Note : Only Admins can create a Student type user

### For Subject (Requires Authentication)

- ` GET -/api/subject` - Retrieves All Subject

```sh
curl  -X GET \
  'localhost:8080/api/subject' \
  --header 'Accept: */*' \
  --header 'Authorization: Bearer d18ee700-4718-4941-9d39-ef7836fce549'
```

> Note : Only Admins can Retreives subjects

- `POST -/api/subject`- Creates subject

```sh
curl  -X POST \
  'localhost:8080/api/subject' \
  --header 'Accept: */*' \
  --header 'Authorization: Bearer 74c54a3f-a867-4b49-b3c8-f813a4eb3fb3' \
  --header 'Content-Type: application/json' \
  --data-raw '[
  {
    "name": "Computer Graphics"
  },
  {
    "name": "DBMS"
  }
]'
```

> Note : Only Admins can create subjects

### Auth (No Authentication)

- `POST - /api/auth/login` - To login and generate token

```sh
curl  -X POST \
  'localhost:8080/api/auth/login' \
  --header 'Accept: */*' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "email":"john@gmail.com",
  "password":"pass123"
}'
```

- `POST - /api/auth/register` - To register an admin user

```sh
curl  -X POST \
  'localhost:8080/api/auth/register' \
  --header 'Accept: */*' \
  --header 'User-Agent: Thunder Client (https://www.thunderclient.com)' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "John Admin",
  "address": "Pune",
  "email": "john_admin@gmail.com",
  "password": "iamadmin123"
}'
```
