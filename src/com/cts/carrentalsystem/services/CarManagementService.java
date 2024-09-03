package com.cts.carrentalsystem.services;

import java.util.Scanner;

/**
 * CarManagementService provides an interface for car management operations.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
public interface CarManagementService {

    /**
     * Adds a new car.
     *
     * @param scanner taking input from the user details of the car to be added.
     */
    void addNewCar(Scanner scanner);

    /**
     * Retrieves a car by ID.
     *
     * @param scanner the for taking input car ID of the car to be retrieved.
     */
    void viewCarDetails(Scanner scanner);

    /**
     * Retrieves all cars.
     *
     */
    void getAllCars();

    /**
     * Updates the details of an existing car.
     *
     * @param scanner taking input new information for the car to be updated.
     */

    void updateCarInfo(Scanner scanner);

    /**
     * Deletes a car by ID.
     *
     * @param scanner takin input car ID for to delete the requested car.
     */
    void deleteCar(Scanner scanner);
}
