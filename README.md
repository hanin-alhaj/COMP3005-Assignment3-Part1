**COMP3005 Assignment 3: Part 1**

A Java application that connects to PostgreSQL using JDBC to perform CRUD operations on a students table.

**Requirements**
Java JDK 25
PostgreSQL
Maven (PostgreSQL JDBC driver)

**Database Setup**
Create a PostgreSQL database named student_db.
Run the provided SQL script in pgAdmin to create the students table and insert the initial data.

**How to Run**
Open the project in IntelliJ IDEA.
Ensure PostgreSQL is running and the student_db database has been created using the provided SQL script.
Load the Maven dependencies from pom.xml.
Set the DB_PASSWORD environment variable to your PostgreSQL password in IntelliJ's Run Configuration.
Run Main.java to execute the application.
To test each CRUD operation, uncomment the corresponding function call in main() and run the program again.

**Video Demonstration**
https://youtu.be/nnF0xs6EBpo
