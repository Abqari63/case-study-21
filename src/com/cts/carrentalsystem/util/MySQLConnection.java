/**
 * MySQLConnection provides a method to establish a connection to the MySQL database.
 * This class contains the database URL, user credentials, and a method to get a connection.
 * It uses JDBC for database connectivity.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */

package com.cts.carrentalsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    /**
     * The URL of the MySQL database.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental_system";

    /**
     * The username for accessing the MySQL database.
     */
    private static final String USER = "root";

    /**
     * The password for accessing the MySQL database.
     */
    private static final String PASSWORD = "AB63qa87@";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return a Connection object to the MySQL database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
