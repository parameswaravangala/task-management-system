# Task Management System 
## Overview

Task Management API is a simple Spring Boot application that demonstrates how to implement JWT (JSON Web Token) authentication for securing RESTful APIs. It provides endpoints to perform CRUD operations on task.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Authorization Server
- MySql
- Flyway
- OAuth2 
- JWT
- OpenAPI Doc
- Docker Compose
# Database
MySQL Docker container is used for database and configured in dockercompose.yml file
## Features
1. JWT Token-based Authentication:- Secures API endpoints using JWT tokens.
2. Task Management API:- Rest API to perform CRUD operation on task.

## Prerequisites

Make sure you have the following installed on your machine:
- <b>Java 17 or higher</b>
- <b>Maven</b>
- <b>Docker</b>

## Getting Started

Follow these steps to set up and run the project on your local machine.

### Step 1: Clone the Project

1. Open a terminal on your computer.

2. Clone the project repository by running the following command:
   ```bash
   git clone https://github.com/parameswaravangala/task-management-system.git
   cd task-management-system

### Step 2: Build and Run Project
1. Maven clean install using the following command::
   ```bash
   mvn clean install

2. Run the Project following docker compose command::
   ```bash
   docker-compose up

03. Open your web browser and visit http://localhost:8080/swagger-ui/index.html to access the Swagger-Ui

## Database schema

![Authorization Database](https://github.com/parameswaravangala/task-management-system/blob/main/auth-db.png)

![Task Management Database](https://github.com/parameswaravangala/task-management-system/blob/main/task-mgmt-db.png)

## Test Data for authentication and authorization
# Users
| User Name | password | Roles  | Permissions           |
|-----------|----------|--------|-----------------------|
| user      | password | user   | task:read             |
| admin     | password | admin  | task:read, task:write |

# Clients
| Client Id              | Client Secret |
|------------------------|---------------|
| task-management-client | secret        |


