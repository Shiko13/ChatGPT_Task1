# Todo List Application

This is a simple Todo List application built with Spring Boot, Hibernate, and MySQL. It allows users to create, read, update, and delete todo items, each of which has a title and a description.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL database

## Getting Started

Follow these steps to run the application:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/todo-list-app.git

## Instruction

1. Create a MySQL database. You can use the following SQL script as a reference:
   CREATE DATABASE todo_db;

2. Update the application.properties file with your database connection details. 
Open src/main/resources/application.properties and modify the following properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password

3. Build the application using Maven:
   cd todo-list-app
   mvn clean package

4. Run the application:
   java -jar target/todo-list-app-1.0.0.jar

The application should be accessible at http://localhost:8080/api/todo-items.

API Endpoints
GET /api/todo-items: Retrieve all todo items.
GET /api/todo-items/{id}: Retrieve a specific todo item by ID.
POST /api/todo-items: Create a new todo item.
PUT /api/todo-items/{id}: Update a todo item by ID.
DELETE /api/todo-items/{id}: Delete a todo item by ID.
License
This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments
This project was created as a learning exercise with Spring Boot and MySQL.
Feel free to contribute or use it as a base for your projects.

## Questions and answers
- Was it easy to complete the task using AI?
Basically yes, but there were the problems with tests.
- How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics)
Basic app about 5 minutes, tests and coverage about 30 minutes
- Was the code ready to run after generation? What did you have to change to make it usable?
Almost. I needed to add some properties for database connection, a few dependencies and getter/setter for entities.
- Which challenges did you face during completion of the task?
The tests were most complicated part.
- Which specific prompts you learned as a good practice to complete the task?
Short phrases with specific rules, code in '''






