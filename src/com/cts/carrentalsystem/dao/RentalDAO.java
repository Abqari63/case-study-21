/**
 * RentalDAO provides an interface for rental transactions in the car rental system.
 *
 * @version 1.0.0
 * @since 2024-09-03
 */
package com.cts.carrentalsystem.dao;

import com.cts.carrentalsystem.model.Rental;
import java.util.List;

public interface RentalDAO {

    /**
     * Rents a car by adding a new rental record to the database.
     *
     * @param rental The Rental object containing the rental details.
     */
    void rentCar(Rental rental);

    /**
     * Returns a rented car by updating the return_date field and setting the car as available.
     *
     * @param rentalId The ID of the rental record to be updated.
     */
    void returnCar(int rentalId);

    /**
     * Retrieves a list of currently rented cars (i.e., rentals with no return date).
     *
     * @return A list of Rental objects representing the currently rented cars.
     */
    List<Rental> getRentedCars();
}
