## Requirements
- Java
- Maven

You must host a database server in port 3306. For this purpose I used XAMPP with MySQL.

On startup, the application will initialize the database with the sql script sql/createtables.sql

## Running the project

It requires the database server to be running.

## Initializing the database and building the project

	mvn sql:execute@create-tables install -Plocal

### Running the service with Maven

	mvn spring-boot:run -Plocal
