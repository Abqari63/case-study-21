/**
 * The RentalDAOImpl class provides methods to handle rental transactions in the car rental system.
 * It includes methods to rent a car, retrieve rented cars, and update car availability status.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
package com.cts.carrentalsystem.dao.impl;

import com.cts.carrentalsystem.dao.RentalDAO;
import com.cts.carrentalsystem.util.MySQLConnection;
import com.cts.carrentalsystem.model.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RentalDAOImpl implements RentalDAO {

    @Override
    public void rentCar(Rental rental) {
        String query = "INSERT INTO Rental (car_id, customer_name, customer_phone, rental_date, return_date) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, rental.getCarId());
            statement.setString(2, rental.getCustomerName());
            statement.setString(3, rental.getCustomerPhone());
            statement.setDate(4, new java.sql.Date(rental.getRentalDate().getTime()));
            statement.setNull(5, Types.DATE);

            statement.executeUpdate();
            updateCarAvailability(rental.getCarId(), false);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeResources(connection, statement, null);
        }
    }

    @Override
    public void returnCar(int rentalId) {
        String updateRentalQuery = "UPDATE Rental SET return_date = ? WHERE rental_id = ?";
        String getCarIdQuery = "SELECT car_id FROM Rental WHERE rental_id = ?";
        Connection connection = null;
        PreparedStatement updateRentalStatement = null;
        PreparedStatement getCarIdStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getConnection();

            updateRentalStatement = connection.prepareStatement(updateRentalQuery);
            updateRentalStatement.setDate(1, new java.sql.Date(new Date().getTime()));
            updateRentalStatement.setInt(2, rentalId);
            updateRentalStatement.executeUpdate();

            int carId = 0;
            getCarIdStatement = connection.prepareStatement(getCarIdQuery);
            getCarIdStatement.setInt(1, rentalId);
            resultSet = getCarIdStatement.executeQuery();
            if (resultSet.next()) {
                carId = resultSet.getInt("car_id");
            } else {
                System.out.println("Rental record not found.");
                return;
            }

            updateCarAvailability(carId, true);
            System.out.println("Car returned successfully.");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeResources(connection, updateRentalStatement, null);
            closeResources(null, getCarIdStatement, resultSet);
        }
    }

    @Override
    public List<Rental> getRentedCars() {
        String query = "SELECT * FROM Rental WHERE return_date IS NULL";
        List<Rental> rentals = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Rental rental = new Rental();
                rental.setRentalId(resultSet.getInt("rental_id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setCustomerName(resultSet.getString("customer_name"));
                rental.setCustomerPhone(resultSet.getString("customer_phone"));
                rental.setRentalDate(resultSet.getDate("rental_date"));
                rental.setReturnDate(resultSet.getDate("return_date"));

                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return rentals;
    }

    private void updateCarAvailability(int carId, boolean available) {
        String query = "UPDATE Car SET available_for_rent = ? WHERE car_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, available);
            statement.setInt(2, carId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeResources(connection, statement, null);
        }
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
