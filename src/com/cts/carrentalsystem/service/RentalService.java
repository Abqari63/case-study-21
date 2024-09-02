/**
 * The RentalService class provides methods to handle rental transactions in the car rental system.
 * It includes methods to rent a car, retrieve rented cars, and update car availability status.
 *
 * @version 1.0.0
 * @author Abqari Abbas
 * @since 2024-09-03
 */

package com.cts.carrentalsystem.service;

import com.cts.carrentalsystem.util.MySQLConnection;
import com.cts.carrentalsystem.model.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RentalService {

    /**
     * Rents a car by adding a new rental record to the database.
     *
     * @param rental The Rental object containing the rental details.
     */
    public void rentCar(Rental rental) {
        String query = "INSERT INTO Rental (car_id, customer_name, customer_phone, rental_date, return_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, rental.getCarId());
            statement.setString(2, rental.getCustomerName());
            statement.setString(3, rental.getCustomerPhone());
            statement.setDate(4, new java.sql.Date(rental.getRentalDate().getTime()));
            statement.setNull(5, Types.DATE);

            statement.executeUpdate();
            updateCarAvailability(rental.getCarId(), false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a rented car by updating the return_date field and setting the car as available.
     *
     * @param rentalId The ID of the rental record to be updated.
     */
    public void returnCar(int rentalId) {
        String updateRentalQuery = "UPDATE Rental SET return_date = ? WHERE rental_id = ?";
        String getCarIdQuery = "SELECT car_id FROM Rental WHERE rental_id = ?";

        try (Connection connection = MySQLConnection.getConnection()) {

            try (PreparedStatement updateRentalStatement = connection.prepareStatement(updateRentalQuery)) {
                updateRentalStatement.setDate(1, new java.sql.Date(new Date().getTime()));
                updateRentalStatement.setInt(2, rentalId);
                updateRentalStatement.executeUpdate();
            }

            int carId;
            try (PreparedStatement getCarIdStatement = connection.prepareStatement(getCarIdQuery)) {
                getCarIdStatement.setInt(1, rentalId);
                try (ResultSet resultSet = getCarIdStatement.executeQuery()) {
                    if (resultSet.next()) {
                        carId = resultSet.getInt("car_id");
                    } else {
                        System.out.println("Rental record not found.");
                        return;
                    }
                }
            }

            // Update the car availability
            updateCarAvailability(carId, true);
            System.out.println("Car returned successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of currently rented cars (i.e., rentals with no return date).
     *
     * @return A list of Rental objects representing the currently rented cars.
     */
    public List<Rental> getRentedCars() {
        String query = "SELECT * FROM Rental WHERE return_date IS NULL";
        List<Rental> rentals = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
            e.printStackTrace();
        }

        return rentals;
    }

    /**
     * Updates the availability status of a car.
     *
     * @param carId    The ID of the car to be updated.
     * @param available The new availability status of the car.
     */
    private void updateCarAvailability(int carId, boolean available) {
        String query = "UPDATE Car SET available_for_rent = ? WHERE car_id = ?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, available);
            statement.setInt(2, carId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
