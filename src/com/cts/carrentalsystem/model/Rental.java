/**
 * The Rental class represents a rental transaction in the car rental system.
 * It contains attributes such as rentalId, carId, customerName, customerPhone,
 * rentalDate, and returnDate. It also provides getter and setter methods to
 * access and modify these attributes.
 *
 * @version 1.0.0
 * @author Abqari Abbas
 * @since 2024-09-03
 */

package com.cts.carrentalsystem.model;

import java.util.Date;

public class Rental {
    /**
     * The unique identifier for the rental transaction.
     */
    private int rentalId;

    /**
     * The unique identifier for the rented car.
     */
    private int carId;

    /**
     * The name of the customer renting the car.
     */
    private String customerName;

    /**
     * The phone number of the customer renting the car.
     */
    private String customerPhone;

    /**
     * The date when the car was rented.
     */
    private Date rentalDate;

    /**
     * The date when the car is expected to be returned.
     */
    private Date returnDate;

    /**
     * Gets the unique identifier for the rental transaction.
     *
     * @return the rental ID
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * Sets the unique identifier for the rental transaction.
     *
     * @param rentalId the rental ID
     */
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * Gets the unique identifier for the rented car.
     *
     * @return the car ID
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Sets the unique identifier for the rented car.
     *
     * @param carId the car ID
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Gets the name of the customer renting the car.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer renting the car.
     *
     * @param customerName the customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the phone number of the customer renting the car.
     *
     * @return the customer phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the phone number of the customer renting the car.
     *
     * @param customerPhone the customer phone number
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Gets the date when the car was rented.
     *
     * @return the rental date
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * Sets the date when the car was rented.
     *
     * @param rentalDate the rental date
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Gets the date when the car is returned.
     *
     * @return the return date
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date when the car is expected to be returned.
     *
     * @param returnDate the return date
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
