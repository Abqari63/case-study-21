/**
 * CarDAOImpl provides methods to perform CRUD operations on the Car database.
 * This class interacts with the MySQL database to add, retrieve, update, and delete car records.
 * It uses JDBC for database connectivity.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
package com.cts.carrentalsystem.dao.impl;

import com.cts.carrentalsystem.dao.CarDAO;
import com.cts.carrentalsystem.util.MySQLConnection;
import com.cts.carrentalsystem.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    @Override
    public void addCar(Car car) {
        String query = "INSERT INTO Car (make, model, year, price_per_day, available_for_rent) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPricePerDay());
            statement.setBoolean(5, car.getAvailableForRent());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);
        }
    }

    @Override
    public Car getCar(int carId) {
        String query = "SELECT * FROM Car WHERE car_id = ?";
        Car car = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, carId);
            resultSet = statement.executeQuery();

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
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return car;
    }

    @Override
    public List<Car> getAllCars() {
        String query = "SELECT * FROM Car";
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

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
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return cars;
    }

    @Override
    public void updateCar(Car car) {
        String query = "UPDATE Car SET make = ?, model = ?, year = ?, price_per_day = ?, available_for_rent = ? WHERE car_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySQLConnection.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPricePerDay());
            statement.setBoolean(5, car.getAvailableForRent());
            statement.setInt(6, car.getCarId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);
        }
    }

    @Override
    public void deleteCar(int carId) {
        String deleteQueryFromRental = "DELETE FROM rental WHERE car_id = ?";
        String deleteQueryFromCar = "DELETE FROM Car WHERE car_id = ?";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement stmt = null;

        try {
            connection = MySQLConnection.getConnection();
            deleteStmt = connection.prepareStatement(deleteQueryFromRental);
            deleteStmt.setInt(1, carId);
            deleteStmt.executeUpdate();

            stmt = connection.prepareStatement(deleteQueryFromCar);
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, deleteStmt, null);
            closeResources(null, stmt, null);
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
