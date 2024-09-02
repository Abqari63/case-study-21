/**
 * CarService provides methods to perform CRUD operations on the Car database.
 * This class interacts with the MySQL database to add, retrieve, update, and delete car records.
 * It uses JDBC for database connectivity.
 *
 * @version 1.0.0
 * @author Abqari Abbas
 * @since 2024-09-03
 */

package com.cts.carrentalsystem.service;

import com.cts.carrentalsystem.util.MySQLConnection;
import com.cts.carrentalsystem.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    /**
     * Adds a new car to the database.
     *
     * @param car the Car object containing details of the car to be added.
     */
    public void addCar(Car car) {
        String query = "INSERT INTO Car (make, model, year, price_per_day, available_for_rent) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPricePerDay());
            statement.setBoolean(5, car.getAvailableForRent());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a car from the database using the car ID.
     *
     * @param carId the ID of the car to be retrieved.
     * @return the Car object if found, otherwise null.
     */
    public Car getCar(int carId) {
        String query = "SELECT * FROM Car WHERE car_id = ?";
        Car car = null;

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                car.setAvailableForRent(resultSet.getBoolean("available_for_rent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    /**
     * Retrieves a list of all cars from the database.
     *
     * @return a List of Car objects representing all cars in the database.
     */
    public List<Car> getAllCars() {
        String query = "SELECT * FROM Car";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                car.setAvailableForRent(resultSet.getBoolean("available_for_rent"));

                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    /**
     * Updates the details of an existing car in the database.
     *
     * @param car the Car object containing the updated details.
     */
    public void updateCar(Car car) {
        String query = "UPDATE Car SET make = ?, model = ?, year = ?, price_per_day = ?, available_for_rent = ? WHERE car_id = ?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPricePerDay());
            statement.setBoolean(5, car.getAvailableForRent());
            statement.setInt(6, car.getCarId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a car from the database using the car ID.
     *
     * @param carId the ID of the car to be deleted.
     */
    public void deleteCar(int carId) {
        String deleteQueryFromRental = "DELETE FROM rental where car_id = ?";
        String deleteQueryFromCar = "DELETE FROM Car WHERE car_id = ?";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteQueryFromRental)) {

            deleteStmt.setInt(1, carId);
            deleteStmt.executeUpdate();

            PreparedStatement stmt = connection.prepareStatement(deleteQueryFromCar);
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
