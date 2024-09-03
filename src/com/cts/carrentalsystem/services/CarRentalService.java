package com.cts.carrentalsystem.services;

import java.util.Scanner;

/**
 * CarRentalService provides an interface for car rental operations.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
public interface CarRentalService {

    /**
     * Rents a car.
     *
     * @param scanner the Rental object containing rental details.
     */
    void rentCar(Scanner scanner);

    /**
     * Returns a rented car.
     *
     * @param scanner the ID of the rental record to be updated.
     */

    void returnCar(Scanner scanner);

    /**
     * Retrieves a list of currently rented cars.
     *
     */
    void getRentedCars();
}
