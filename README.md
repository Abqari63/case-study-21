# Car Rental System

## Overview

This is a console-based Car Rental System implemented in Java using JDBC for database interactions. The system allows users to manage cars and handle car rentals.

## Prerequisites

- Java Development Kit (JDK17)
- intelliJ IDE
- MySQL Database Server, workbench (to see changes), command-line client (to execute MySQL commands)
- MySQL JDBC Driver (in my case *mysql-connector-j-9.0.0.jar*)

## Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/Abqari63/case-study-21
    cd car-rental-system
    ```

2. **Create the database and tables:**

    ```sql
    CREATE DATABASE car_rental_system;

    USE car_rental_system;

    CREATE TABLE Car (
        car_id INT AUTO_INCREMENT PRIMARY KEY,
        make VARCHAR(50),
        model VARCHAR(50),
        year INT,
        price_per_day DECIMAL(10, 2),
        available_for_rent BOOLEAN
    );

    CREATE TABLE Rental (
        rental_id INT AUTO_INCREMENT PRIMARY KEY,
        car_id INT,
        customer_name VARCHAR(100),
        customer_phone VARCHAR(15),
        rental_date DATE,
        return_date DATE,
        FOREIGN KEY (car_id) REFERENCES Car(car_id)
    );
    ```

3. **Configure the database connection:**

   Update the `DatabaseConnection.java` file with your MySQL credentials.

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental_system";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    ```

4. **Compile and run the application on windows powershell:**

    ```sh
    javac -d bin src/code/*.java
    java -cp "bin;lib/mysql-connector-j-9.0.0.jar" code.CarRentalSystem
    ```

## Usage

1. Run the application.
2. Follow the menu prompts to manage cars and rentals.
