/**
 * CarDAO provides an interface for CRUD operations on the Car database.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
package com.cts.carrentalsystem.dao;

import com.cts.carrentalsystem.model.Car;
import java.util.List;

public interface CarDAO {

    /**
     * Adds a new car to the database.
     *
     * @param car the Car object containing details of the car to be added.
     */
    void addCar(Car car);

    /**
     * Retrieves a car from the database using the car ID.
     *
     * @param carId the ID of the car to be retrieved.
     * @return the Car object if found, otherwise null.
     */
    Car getCar(int carId);

    /**
     * Retrieves a list of all cars from the database.
     *
     * @return a List of Car objects representing all cars in the database.
     */
    List<Car> getAllCars();

    /**
     * Updates the details of an existing car in the database.
     *
     * @param car the Car object containing the updated details.
     */
    void updateCar(Car car);

    /**
     * Deletes a car from the database using the car ID.
     *
     * @param carId the ID of the car to be deleted.
     */
    void deleteCar(int carId);
}
